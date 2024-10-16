package io.anura.sdk.exceptions;

/**
 * Thrown when a 5XX response is returned from the Anura Direct API.
 */
public class AnuraServerException extends AnuraException {
    public AnuraServerException(String message) {
        super(message);
    }
}
