package pl.devfinder.infrastructure.configuration;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.DevfinderApplication;

@ActiveProfiles("test")
@Import(PersistenceContainerTestConfiguration.class)
@SpringBootTest(
    classes = DevfinderApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
public abstract class AbstractIT {

    @LocalServerPort
    protected int port;

    @Value("${server.servlet.context-path}")
    protected String basePath;

    @AfterEach
    void afterEach() {

    }
}
