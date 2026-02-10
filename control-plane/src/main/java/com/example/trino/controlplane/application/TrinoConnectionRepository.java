package com.example.trino.controlplane.application;

import com.example.trino.controlplane.domain.TrinoConnectionConfig;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrinoConnectionRepository {

    TrinoConnectionConfig save(TrinoConnectionConfig config);

    List<TrinoConnectionConfig> findAll();

    Optional<TrinoConnectionConfig> findById(UUID id);

    void deleteById(UUID id);
}
