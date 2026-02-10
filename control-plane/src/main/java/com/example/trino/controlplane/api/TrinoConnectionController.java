package com.example.trino.controlplane.api;

import com.example.trino.controlplane.application.TrinoConnectionService;
import com.example.trino.controlplane.domain.ConnectionValidationResult;
import com.example.trino.controlplane.domain.TrinoConnectionConfig;
import com.example.trino.controlplane.domain.TrinoConnectionRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/connections")
public class TrinoConnectionController {

    private final TrinoConnectionService service;

    public TrinoConnectionController(TrinoConnectionService service) {
        this.service = service;
    }

    @PostMapping
    public TrinoConnectionConfig create(@Valid @RequestBody TrinoConnectionRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<TrinoConnectionConfig> list() {
        return service.list();
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable UUID id) {
        service.delete(id);
        return Map.of("status", "deleted");
    }

    @PostMapping("/dry-run")
    public List<ConnectionValidationResult> dryRun(@Valid @RequestBody TrinoConnectionRequest request) {
        return service.dryRun(request);
    }
}
