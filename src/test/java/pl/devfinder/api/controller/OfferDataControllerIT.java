package pl.devfinder.api.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.devfinder.infrastructure.configuration.AbstractITWithoutSecurity;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class OfferDataControllerIT extends AbstractITWithoutSecurity {

    private final TestRestTemplate testRestTemplate;

    @Test
    void thatOffersListPageNotRequiredSigningIn() {
        String url = String.format("http://localhost:%s%s%s", port, basePath, OfferDataController.OFFERS_LIST);
        String page = this.testRestTemplate.getForObject(url, String.class);
        Assertions.assertThat(page).contains("Job offers (found:");
    }

    @Test
    void thatOfferDetailPageNotRequiredSigningIn() {
        String url = String.format("http://localhost:%s%s%s", port, basePath, "/offer/1");
        String page = this.testRestTemplate.getForObject(url, String.class);
        Assertions.assertThat(page).contains("Offer nr");
    }

}

