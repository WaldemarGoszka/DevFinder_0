package pl.devfinder.infrastructure.support;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ControllerTestSupport {
    ObjectMapper getObjectMapper();
}