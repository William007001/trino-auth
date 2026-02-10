package com.example.trino.controlplane.plugin;

import com.example.trino.controlplane.domain.ConnectionValidationResult;
import com.example.trino.controlplane.domain.TrinoConnectionRequest;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class AuthModeValidator implements ConnectionPolicyValidator {

    private static final Set<String> SUPPORTED_AUTH_MODES = Set.of("NONE", "PASSWORD", "JWT", "KERBEROS");

    @Override
    public String name() {
        return "auth-mode";
    }

    @Override
    public ConnectionValidationResult validate(TrinoConnectionRequest request) {
        if (!SUPPORTED_AUTH_MODES.contains(request.authMode().toUpperCase())) {
            return ConnectionValidationResult.fail("当前仅支持认证模式: " + SUPPORTED_AUTH_MODES);
        }

        return ConnectionValidationResult.ok("认证模式检查通过");
    }
}
