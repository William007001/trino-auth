package com.example.trino.controlplane.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record TrinoConnectionRequest(
        @NotBlank String name,
        @NotBlank String host,
        @Min(1) @Max(65535) int port,
        @NotBlank String catalog,
        @NotBlank String schema,
        @NotBlank String authMode,
        @NotNull Map<String, String> attributes
) {
}
