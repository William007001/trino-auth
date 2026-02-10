package com.example.trino.controlplane.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record TrinoConnectionConfig(
        UUID id,
        String name,
        String host,
        int port,
        String catalog,
        String schema,
        String authMode,
        Map<String, String> attributes,
        Instant createdAt,
        Instant updatedAt
) {
}
