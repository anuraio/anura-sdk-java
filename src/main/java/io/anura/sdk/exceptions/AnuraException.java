package io.anura.sdk.exceptions;

/**
 * Thrown when an error is returned from the Anura Direct API.
 */
public class AnuraException extends RuntimeException {
    public AnuraException(String message) {
        super(message);
    }
}
