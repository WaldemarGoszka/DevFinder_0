package pl.devfinder.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
public interface ControllerTestSupport {
ObjectMapper getObjectMapper();
}