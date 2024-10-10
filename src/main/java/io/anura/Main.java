package io.anura;

import io.anura.sdk.AnuraDirect;
import io.anura.sdk.DirectRequest;
import io.anura.sdk.DirectRequestBuilder;
import io.anura.sdk.DirectResult;
import io.anura.sdk.exceptions.AnuraClientException;
import io.anura.sdk.exceptions.AnuraException;
import io.anura.sdk.exceptions.AnuraServerException;

import java.io.IOException;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        AnuraDirect direct = new AnuraDirect(true);
        DirectRequestBuilder builder = new DirectRequestBuilder();
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "java-data");

        DirectRequest request = builder
                                .setInstanceId("1854561621")
                                .setSource("java-source")
                                .setCampaign("java-campaign")
                                .setIpAddress("127.0.0.")
                                .setUserAgent("my-user-agent")
                                .setAppId("my-app-id")
                                .setDeviceId("my-device-id")
                                .setAdditionalData(map)
                                .build();


        DirectResult result;
        try {
            result = direct.getResult(request);
        } catch (AnuraClientException e) {
            // Handle client error.
        } catch (AnuraServerException e) {
            // Handle server error.
        } catch (AnuraException e) {
            // Handle general "catch-all" error.
        }
    }
}