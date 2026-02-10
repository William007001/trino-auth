package com.example.trino.controlplane.domain;

public record ConnectionValidationResult(boolean success, String message) {

    public static ConnectionValidationResult ok(String message) {
        return new ConnectionValidationResult(true, message);
    }

    public static ConnectionValidationResult fail(String message) {
        return new ConnectionValidationResult(false, message);
    }
}
