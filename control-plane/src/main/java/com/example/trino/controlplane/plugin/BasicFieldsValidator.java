package com.example.trino.controlplane.plugin;

import com.example.trino.controlplane.domain.ConnectionValidationResult;
import com.example.trino.controlplane.domain.TrinoConnectionRequest;
import org.springframework.stereotype.Component;

@Component
public class BasicFieldsValidator implements ConnectionPolicyValidator {

    @Override
    public String name() {
        return "basic-fields";
    }

    @Override
    public ConnectionValidationResult validate(TrinoConnectionRequest request) {
        if (request.host().contains(" ")) {
            return ConnectionValidationResult.fail("host 不能包含空格");
        }

        if (request.name().length() > 64) {
            return ConnectionValidationResult.fail("name 长度不能超过 64");
        }

        return ConnectionValidationResult.ok("字段格式检查通过");
    }
}
