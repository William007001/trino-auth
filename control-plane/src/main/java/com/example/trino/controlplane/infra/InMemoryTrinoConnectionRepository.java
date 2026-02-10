package com.example.trino.controlplane.infra;

import com.example.trino.controlplane.application.TrinoConnectionRepository;
import com.example.trino.controlplane.domain.TrinoConnectionConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTrinoConnectionRepository implements TrinoConnectionRepository {

    private final Map<UUID, TrinoConnectionConfig> storage = new ConcurrentHashMap<>();

    @Override
    public TrinoConnectionConfig save(TrinoConnectionConfig config) {
        storage.put(config.id(), config);
        return config;
    }

    @Override
    public List<TrinoConnectionConfig> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<TrinoConnectionConfig> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
