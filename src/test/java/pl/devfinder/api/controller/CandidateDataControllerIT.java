package pl.devfinder.api.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.devfinder.infrastructure.configuration.AbstractIT;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CandidateDataControllerIT extends AbstractIT {

    private final TestRestTemplate testRestTemplate;
    @Test
    void thatPageNotRequiredSigningIn() {
        String url = String.format("http://localhost:%s%s%s", port, basePath,CandidateDataController.CANDIDATES_LIST);
        String page = this.testRestTemplate.getForObject(url, String.class);
        Assertions.assertThat(page).contains("Available Candidates");
    }
    @Test
    void thatCandidateDetailPageNotRequiredSigningIn() {
        String url = String.format("http://localhost:%s%s%s", port, basePath,"/candidate/1");
        String page = this.testRestTemplate.getForObject(url, String.class);
        Assertions.assertThat(page).contains("Candidate nr");
    }

}

