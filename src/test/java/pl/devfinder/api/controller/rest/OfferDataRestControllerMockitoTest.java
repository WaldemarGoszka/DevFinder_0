package pl.devfinder.api.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.api.dto.OffersDTO;
import pl.devfinder.api.dto.mapper.OfferDetailsMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.business.OfferService;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.search.OfferSearchCriteria;
import pl.devfinder.util.OfferDetailsDTOFixtures;
import pl.devfinder.util.OfferFixtures;
import pl.devfinder.util.OfferRowDTOFixtures;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferDataRestControllerMockitoTest {
    @Mock
    private OfferService offerService;
    @Mock
    private OfferDetailsMapper offerDetailsMapper;
    @Mock
    private OfferRowMapper offerRowMapper;
    @InjectMocks
    private OfferDataRestController offerDataRestController;
    @Test
    void getOffersList() {
        // given
        OfferSearchCriteria offerSearchCriteria = new OfferSearchCriteria();
        Offer offer = OfferFixtures.someOffer1();
        Page<Offer> page = new PageImpl<>(List.of(offer));
        OfferRowDTO offerRowDTO = OfferRowDTOFixtures.someOfferRowDTO1();
        List<OfferRowDTO> offerRowDTOs = List.of(offerRowDTO);
        OffersDTO expectedOffersDTO = OffersDTO.builder().offerRowDTOs(offerRowDTOs).build();

        // when
        when(offerService.findAllByCriteria(offerSearchCriteria)).thenReturn(page);
        when(offerRowMapper.map(offer)).thenReturn(offerRowDTO);

        ResponseEntity<OffersDTO> responseEntity = offerDataRestController.getOffersList(offerSearchCriteria);
        OffersDTO actualOffersDTO = responseEntity.getBody();

        // then
        verify(offerService).findAllByCriteria(offerSearchCriteria);
        verify(offerRowMapper).map(offer);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualOffersDTO);
        assertEquals(expectedOffersDTO, actualOffersDTO);
    }
    @Test
    void getOfferDetails() {
        // given
        Offer offer = OfferFixtures.someOffer1();
        Long offerId = offer.getOfferId();
        OfferDetailsDTO expectedOfferDetailsDTO = OfferDetailsDTOFixtures.someOfferDetailsDTO1();

        // when
        when(offerService.findById(offerId)).thenReturn(offer);
        when(offerDetailsMapper.map(offer)).thenReturn(expectedOfferDetailsDTO);

        ResponseEntity<OfferDetailsDTO> responseEntity = offerDataRestController.getOfferDetails(offerId);
        OfferDetailsDTO actualOfferDetailsDTO = responseEntity.getBody();

        // then
        verify(offerService).findById(offerId);
        verify(offerDetailsMapper).map(offer);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualOfferDetailsDTO);
        assertEquals(expectedOfferDetailsDTO, actualOfferDetailsDTO);
    }

    @Test
    void getOfferDetailsOfferNotFound() {
        // given
        Long offerId = 1L;

        // when
        when(offerService.findById(offerId)).thenReturn(null);

        ResponseEntity<OfferDetailsDTO> responseEntity = offerDataRestController.getOfferDetails(offerId);

        // then
        verify(offerService).findById(offerId);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
    }

}