package io.anura.sdk.exceptions;

/**
 * Thrown when a 4XX Response is returned from the Anura Direct API.
 */
public class AnuraClientException extends AnuraException {
    public AnuraClientException(String message) {
        super(message);
    }
}
