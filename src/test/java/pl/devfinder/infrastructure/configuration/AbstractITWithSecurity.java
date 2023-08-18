package pl.devfinder.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.DevfinderApplication;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ActiveProfiles("test")
@Import(PersistenceContainerTestConfiguration.class)
@SpringBootTest(
    classes = DevfinderApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
public abstract class AbstractITWithSecurity implements
        ControllerTestSupport,
        AuthenticationTestSupport {

    protected static WireMockServer wireMockServer;

    @LocalServerPort
    protected int port;

    private String jSessionIdValue;
    @Test
    void contextLoaded() {
        Assertions.assertTrue(true, "Context loaded");
    }
    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(
                wireMockConfig()
                        .port(9999)
                        .extensions(new ResponseTemplateTransformer(false))
        );
        wireMockServer.start();
    }


    @BeforeEach
    void beforeEach() {
        jSessionIdValue = login("user1", "test")
                .and()
                .cookie("JSESSIONID")
                .header(HttpHeaders.LOCATION, "http://localhost:%s%s/".formatted(port, basePath))
                .extract()
                .cookie("JSESSIONID");
    }
    @AfterEach
    void afterEach() {
        logout()
                .and()
                .cookie("JSESSIONID", "");
        jSessionIdValue = null;
        wireMockServer.resetAll();
    }
    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }


    @Value("${server.servlet.context-path}")
    protected String basePath;

    @Autowired
    protected ObjectMapper objectMapper;
    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    public RequestSpecification requestSpecification() {
        return RestAssured
                .given()
                .config(getConfig())
                .basePath(basePath)
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }
    private RestAssuredConfig getConfig() {
        return RestAssuredConfig
                .config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) -> objectMapper));
    }


}
