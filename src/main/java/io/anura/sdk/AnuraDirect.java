package io.anura.sdk;

import com.google.gson.Gson;
import io.anura.sdk.exceptions.AnuraClientException;
import io.anura.sdk.exceptions.AnuraException;
import io.anura.sdk.exceptions.AnuraServerException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AnuraDirect {
    private boolean useHttps;
    private final Gson gson;

    public AnuraDirect(boolean useHttps) {
        this.useHttps = useHttps;
        this.gson = new Gson();
    }

    public DirectResult getResult(DirectRequest directRequest) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HashMap<String, String> queryParams = new HashMap<>();

        if (!directRequest.getInstanceId().isEmpty()) queryParams.put("instance", directRequest.getInstanceId());
        if (!directRequest.getSource().isEmpty()) queryParams.put("source", directRequest.getSource());
        if (!directRequest.getCampaign().isEmpty()) queryParams.put("campaign", directRequest.getCampaign());
        if (!directRequest.getIpAddress().isEmpty()) queryParams.put("ip", directRequest.getIpAddress());
        if (!directRequest.getUserAgent().isEmpty()) queryParams.put("ua", directRequest.getUserAgent());
        if (!directRequest.getAppId().isEmpty()) queryParams.put("app", directRequest.getAppId());
        if (!directRequest.getDeviceId().isEmpty()) queryParams.put("device", directRequest.getDeviceId());
        if (!directRequest.getAdditionalData().isEmpty()) {
            String additionalDataString = this.getAdditionalDataString(directRequest.getAdditionalData());
            queryParams.put("additional", additionalDataString);
        }

        URI uri = this.buildUri(getAPIUrl(), queryParams);
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode < 200 || statusCode > 299) {
            this.handleError(response);
        }

        try {
            client.close();
            return this.gson.fromJson(response.body(), DirectResult.class);
        } catch(Exception ignored) {
            throw new AnuraException("Something went wrong!");
        }
    }

    public boolean isUseHttps() {
        return useHttps;
    }

    public void setUseHttps(boolean useHttps) {
        this.useHttps = useHttps;
    }

    private void handleError(HttpResponse<String> response) throws AnuraClientException, AnuraServerException, AnuraException {
        DirectError error = null;
        try {
            error = this.gson.fromJson(response.body(), DirectError.class);
        } catch (Exception ignored) {
            throw new AnuraException("Unknown error occurred.");
        }

        int statusCode = response.statusCode();
        if (statusCode >= 400 && statusCode <= 499) {
            throw new AnuraClientException(error.getError());
        } else if (statusCode >= 500 && statusCode <= 599) {
            throw new AnuraServerException("Anura Server Error: " + statusCode);
        }  else {
            throw new AnuraException("Unknown error occurred.");
        }
    }

    private String getAPIUrl() {
        if (this.useHttps) {
            return "https://direct.anura.io/direct.json";
        } else {
            return "http://direct.anura.io/direct.json";
        }
    }

    private String getAdditionalDataString(HashMap<String, String> additionalData) {
        return URLEncoder.encode(this.gson.toJson(additionalData), StandardCharsets.UTF_8);
    }

    private URI buildUri(String url, Map<String, String> queryParams) {
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        for (Map.Entry<String, String> entry: queryParams.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        // Removing the trailing "&" at the end of the URI String.
        sb.deleteCharAt(sb.length() - 1);

        return URI.create(sb.toString());
    }
}
