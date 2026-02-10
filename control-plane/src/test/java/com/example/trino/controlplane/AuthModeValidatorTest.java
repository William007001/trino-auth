package com.example.trino.controlplane;

import com.example.trino.controlplane.domain.TrinoConnectionRequest;
import com.example.trino.controlplane.plugin.AuthModeValidator;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthModeValidatorTest {

    @Test
    void shouldRejectUnsupportedMode() {
        AuthModeValidator validator = new AuthModeValidator();
        TrinoConnectionRequest request = new TrinoConnectionRequest(
                "demo",
                "localhost",
                8080,
                "hive",
                "default",
                "CUSTOM",
                Map.of()
        );

        Assertions.assertFalse(validator.validate(request).success());
    }
}
