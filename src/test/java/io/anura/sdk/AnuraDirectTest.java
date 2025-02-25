package io.anura.sdk;

import io.anura.sdk.exceptions.AnuraClientException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AnuraDirectTest {
    @Test
    @DisplayName("Empty Instance Should Throw Anura Client Exception")
    void emptyInstance() {
        DirectRequestBuilder builder = new DirectRequestBuilder();
        DirectRequest request = builder.setIpAddress("127.0.0.1").build();

        AnuraDirect direct = new AnuraDirect(null, true);
        assertThrows(AnuraClientException.class, () -> direct.getResult(request));
    }

    @Test
    @DisplayName("Invalid Instance Should Throw Anura Client Exception")
    void invalidInstance() {
        DirectRequestBuilder builder = new DirectRequestBuilder();
        DirectRequest request = builder.setIpAddress("127.0.0.1").build();

        AnuraDirect direct = new AnuraDirect("abcdefg", true);
        assertThrows(AnuraClientException.class, () -> direct.getResult(request));
    }

    @Test
    @DisplayName("Can add additional data")
    void addAdditionalData() {
     var builder = new DirectRequestBuilder();
     var request = builder.build();

     var additionalDataMap = new HashMap<Integer, String>();
     additionalDataMap.put(1, "test");
     request = builder.setAdditionalData(additionalDataMap).build();

     assertEquals(request.getAdditionalData().get("1"), "test");
    }
}