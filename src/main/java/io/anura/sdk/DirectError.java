package io.anura.sdk;

public class DirectError {
    private final String error;

    public DirectError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}
