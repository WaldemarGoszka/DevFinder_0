package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JustJoinItClient {

    public static final String url = "https://justjoin.it/api/offers";
    private final WebClient webClient;

    public Optional<List<JustJoinItOffer>> getOffers() {
        try {
            List<JustJoinItOffer> result = webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToFlux(JustJoinItOffer.class)
                    .collectList()
                    .block();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<JustJoinItOfferDetails> getOffer(String offerId) {
        try {
            JustJoinItOfferDetails result = webClient
                    .get()
                    .uri(String.format(url + "/" + offerId))
                    .retrieve()
                    .bodyToMono(JustJoinItOfferDetails.class)
                    .block();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
