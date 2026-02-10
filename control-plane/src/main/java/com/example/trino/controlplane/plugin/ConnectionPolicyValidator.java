package com.example.trino.controlplane.plugin;

import com.example.trino.controlplane.domain.ConnectionValidationResult;
import com.example.trino.controlplane.domain.TrinoConnectionRequest;

public interface ConnectionPolicyValidator {

    String name();

    ConnectionValidationResult validate(TrinoConnectionRequest request);
}
