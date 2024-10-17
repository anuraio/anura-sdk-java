package io.anura.sdk;

/**
 * A POJO representing an error message returned from the Anura Direct API.
 */
public class DirectError {
    private final String error;

    public DirectError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}
