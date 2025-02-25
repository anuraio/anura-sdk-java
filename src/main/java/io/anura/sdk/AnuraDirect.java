package io.anura.sdk;

import com.google.gson.Gson;
import io.anura.sdk.exceptions.AnuraClientException;
import io.anura.sdk.exceptions.AnuraException;
import io.anura.sdk.exceptions.AnuraServerException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * An API client for Anura Direct.
 */
public class AnuraDirect {
    private String instance;
    private String apiUrl;
    private boolean useHttps;
    private final Gson gson;

    public AnuraDirect(String instance, boolean useHttps) {
        this.instance = instance;
        this.useHttps = useHttps;
        this.apiUrl = useHttps ? "https://direct.anura.io/direct.json" : "http://direct.anura.io/direct.json";
        this.gson = new Gson();
    }

    /**
     * Gets a result from the Anura Direct API.
     *
     * @param   directRequest           the DirectRequest object to send to Anura Direct for assessment
     * @return                          the result received from Anura Direct
     * @throws IOException              if an I/O error occurs when sending or receiving from Anura Direct
     * @throws InterruptedException     if the request/response process with the Anura Direct API is interrupted
     * @throws AnuraException           if a 4XX, 5XX, or any unknown response is returned from Anura Direct
     */
    public DirectResult getResult(DirectRequest directRequest) throws IOException, InterruptedException, AnuraException {
        HttpClient client = HttpClient.newHttpClient();
        HashMap<String, String> queryParams = new HashMap<>();

        if (this.getInstance() != null && !this.getInstance().isEmpty()) queryParams.put("instance", this.getInstance());
        if (directRequest.getSource() != null && !directRequest.getSource().isEmpty()) queryParams.put("source", directRequest.getSource());
        if (directRequest.getCampaign() != null && !directRequest.getCampaign().isEmpty()) queryParams.put("campaign", directRequest.getCampaign());
        if (directRequest.getIpAddress() != null && !directRequest.getIpAddress().isEmpty()) queryParams.put("ip", directRequest.getIpAddress());
        if (directRequest.getUserAgent() != null && !directRequest.getUserAgent().isEmpty()) queryParams.put("ua", directRequest.getUserAgent());
        if (directRequest.getApp() != null && !directRequest.getApp().isEmpty()) queryParams.put("app", directRequest.getApp());
        if (directRequest.getDevice() != null && !directRequest.getDevice().isEmpty()) queryParams.put("device", directRequest.getDevice());
        if (directRequest.getAdditionalData() != null && !directRequest.getAdditionalData().isEmpty()) {
            String additionalDataString = this.getAdditionalDataString(directRequest.getAdditionalData());
            queryParams.put("additional", additionalDataString);
        }

        URI uri = this.buildUri(this.apiUrl, queryParams);
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode < 200 || statusCode > 299) {
            throw this.getAnuraException(response);
        }

        try {
            client.close();
            return this.gson.fromJson(response.body(), DirectResult.class);
        } catch(Exception ignored) {
            throw new AnuraException("Could not parse JSON body from Anura Direct response.");
        }
    }

    /**
     * Returns whether the {@link AnuraDirect} client is using HTTPS or HTTP.
     *
     * @return  Whether the client is using HTTPS or HTTP
     */
    public boolean isUseHttps() {
        return useHttps;
    }

    /**
     * Sets the AnuraDirect to use the HTTPS or HTTP endpoint for calling the Anura Direct API.
     *
     * @param useHttps  Whether to use HTTPS or HTTP
     */
    public void setUseHttps(boolean useHttps) {
        this.useHttps = useHttps;
        this.apiUrl = useHttps ? "https://direct.anura.io/direct.json" : "http://direct.anura.io/direct.json";
    }

    public String getInstance() { return this.instance; }

    public void setInstance(String instance) { this.instance = instance; }

    private AnuraException getAnuraException(HttpResponse<String> response) {
        DirectError error = null;
        try {
            error = this.gson.fromJson(response.body(), DirectError.class);
        } catch (Exception ignored) {
            return new AnuraException("Could not parse error received from Anura Direct API.");
        }

        int statusCode = response.statusCode();
        if (statusCode >= 400 && statusCode <= 499) {
            return new AnuraClientException(error.getError());
        } else if (statusCode >= 500 && statusCode <= 599) {
            return new AnuraServerException("Anura Server Error: " + statusCode);
        }  else {
            return new AnuraException("Unknown error occurred. Status: " + statusCode);
        }
    }

    private String getAdditionalDataString(HashMap<Integer, String> additionalData) {
        return URLEncoder.encode(this.gson.toJson(additionalData), StandardCharsets.UTF_8);
    }

    private URI buildUri(String url, Map<String, String> queryParams) {
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        for (Map.Entry<String, String> entry: queryParams.entrySet()) {
            boolean shouldEncode = (!entry.getKey().equals("ip") && !entry.getKey().equals("instance"));

            sb.append(entry.getKey())
                .append("=")
                .append(
                    shouldEncode ? URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8) : entry.getValue()
                )
                .append("&");
        }

        sb.deleteCharAt(sb.length() - 1);

        return URI.create(sb.toString());
    }
}
