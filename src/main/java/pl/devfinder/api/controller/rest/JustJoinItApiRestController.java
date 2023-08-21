package pl.devfinder.api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.devfinder.api.controller.exception.JustJoinItApiNotRespondException;
import pl.devfinder.business.JustJoinItApiService;
import pl.devfinder.infrastructure.api_consume.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(JustJoinItApiRestController.BASE_PATH)

public class JustJoinItApiRestController {
    public static final String BASE_PATH = "/api";

    public static final String OFFERS_LIST = "/justjoinit_offers";
    public static final String OFFER_DETAILS = "/justjoinit_offers/{offerId}";
    public static final String OFFERS_ANALYSIS = "/justjoinit_offers_analysis";
    public static final String OFFER_ANALYTICS_TECHNOLOGY = "/justjoinit_offers_analytics_particular_technology/{technology}";

    private final JustJoinItApiService justJoinItApiService;
    private final JustJoinItClient justJoinItClient;


    @GetMapping(value = OFFERS_LIST)
    public ResponseEntity<List<JustJoinItOffer>> getOffersList() {
        List<JustJoinItOffer> offers = justJoinItClient.getOffers()
                .orElseThrow(() -> new JustJoinItApiNotRespondException("Could not find justjoinit offers"));
        return ResponseEntity.ok(offers);
    }

    @GetMapping(value = OFFER_DETAILS)
    public ResponseEntity<JustJoinItOfferDetails> getOfferDetails(@PathVariable String offerId) {
        JustJoinItOfferDetails offer = justJoinItClient.getOffer(offerId)
                .orElseThrow(() -> new JustJoinItApiNotRespondException("Could not find justjoinit offer details for offerId: " + offerId));
        return ResponseEntity.ok(offer);
    }

    @GetMapping(value = OFFERS_ANALYSIS)
    public ResponseEntity<JustJoinItOfferAnalysisResult> getAnalyticsResult() {
        Optional<List<JustJoinItOffer>> offers = justJoinItClient.getOffers();
        JustJoinItOfferAnalysisResult offer = justJoinItApiService.getAnalysisResult(offers)
                .orElseThrow(() -> new JustJoinItApiNotRespondException("Could not process analytics result"));
        return ResponseEntity.ok(offer);
    }

    @GetMapping(value = OFFER_ANALYTICS_TECHNOLOGY)
    public ResponseEntity<JustJoinItOfferAnalysisResultParticularTechnology> getAnalyticsResult(@PathVariable String technology) {
        Optional<List<JustJoinItOffer>> offers = justJoinItClient.getOffers();
        JustJoinItOfferAnalysisResultParticularTechnology offer = justJoinItApiService.getAnalysisForParticularTechnology(offers, technology)
                .orElseThrow(() -> new JustJoinItApiNotRespondException("Could not process analytics result for technology: " + technology));
        return ResponseEntity.ok(offer);
    }


}