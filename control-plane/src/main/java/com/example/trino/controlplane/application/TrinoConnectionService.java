package com.example.trino.controlplane.application;

import com.example.trino.controlplane.domain.ConnectionValidationResult;
import com.example.trino.controlplane.domain.TrinoConnectionConfig;
import com.example.trino.controlplane.domain.TrinoConnectionRequest;
import com.example.trino.controlplane.plugin.ConnectionPolicyValidator;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TrinoConnectionService {

    private final TrinoConnectionRepository repository;
    private final List<ConnectionPolicyValidator> validators;

    public TrinoConnectionService(
            TrinoConnectionRepository repository,
            List<ConnectionPolicyValidator> validators
    ) {
        this.repository = repository;
        this.validators = validators;
    }

    public TrinoConnectionConfig create(TrinoConnectionRequest request) {
        validateRequest(request);

        Instant now = Instant.now();
        TrinoConnectionConfig config = new TrinoConnectionConfig(
                UUID.randomUUID(),
                request.name(),
                request.host(),
                request.port(),
                request.catalog(),
                request.schema(),
                request.authMode(),
                request.attributes(),
                now,
                now
        );

        return repository.save(config);
    }

    public List<TrinoConnectionConfig> list() {
        return repository.findAll();
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<ConnectionValidationResult> dryRun(TrinoConnectionRequest request) {
        return validators.stream().map(v -> v.validate(request)).toList();
    }

    private void validateRequest(TrinoConnectionRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConnectionPolicyValidator validator : validators) {
            ConnectionValidationResult result = validator.validate(request);
            if (!result.success()) {
                errors.add("[" + validator.name() + "] " + result.message());
            }
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }
    }
}
