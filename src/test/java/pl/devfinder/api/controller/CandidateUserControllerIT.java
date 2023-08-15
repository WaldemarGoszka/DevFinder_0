package pl.devfinder.api.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.devfinder.infrastructure.configuration.AbstractIT;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CandidateUserControllerIT extends AbstractIT {

    private final TestRestTemplate testRestTemplate;
    @Test
    void thatCandidateProfilePageRequiredSigningIn() {
        String url = String.format("http://localhost:%s%s%s", port, basePath,CandidateUserController.CANDIDATE_PROFILE);
        String page = this.testRestTemplate.getForObject(url, String.class);
        Assertions.assertThat(page).contains("Log in");
    }
    @Test
    void thatCandidateEditProfilePageRequiredSigningIn() {
        String url = String.format("http://localhost:%s%s%s", port, basePath,CandidateUserController.CANDIDATE_EDIT_PROFILE);
        String page = this.testRestTemplate.getForObject(url, String.class);
        Assertions.assertThat(page).contains("Log in");
    }


}

