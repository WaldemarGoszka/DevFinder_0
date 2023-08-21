package pl.devfinder.api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.api.dto.OffersDTO;
import pl.devfinder.api.dto.mapper.OfferDetailsMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.business.OfferService;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.search.OfferSearchCriteria;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(OfferDataRestController.BASE_PATH)

public class OfferDataRestController {
    public static final String BASE_PATH = "/api";
    public static final String OFFERS_LIST = "/offers";
    public static final String OFFER_DETAILS = "/offer/{offerId}";

    private final OfferService offerService;
    private final OfferDetailsMapper offerDetailsMapper;
    private final OfferRowMapper offerRowMapper;

    @GetMapping(value = OFFERS_LIST)
    public ResponseEntity<OffersDTO> getOffersList(@ModelAttribute OfferSearchCriteria offerSearchCriteria) {
        Page<Offer> allByCriteria = offerService.findAllByCriteria(offerSearchCriteria);
        Page<OfferRowDTO> page = allByCriteria.map(offerRowMapper::map);
        List<OfferRowDTO> content = page.getContent();
        OffersDTO offersDTO = OffersDTO.builder().offerRowDTOs(content).build();
        return ResponseEntity.ok(offersDTO);
    }

    @GetMapping(value = OFFER_DETAILS)
    public ResponseEntity<OfferDetailsDTO> getOfferDetails(@PathVariable Long offerId) {
        Offer offer = offerService.findById(offerId);
        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offer);
        return ResponseEntity.ok(offerDetailsDTO);
    }
}