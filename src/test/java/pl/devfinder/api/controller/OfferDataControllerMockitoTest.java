package pl.devfinder.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.OfferSearchCriteria;
import pl.devfinder.util.OfferDetailsDTOFixtures;
import pl.devfinder.util.OfferFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferDataControllerMockitoTest {
    @Mock
    private UserService userService;
    @Mock
    private CityService cityService;
    @Mock
    private CityMapper cityMapper;
    @Mock
    private SkillService skillService;
    @Mock
    private SkillMapper skillMapper;
    @Mock
    private EmployerService employerService;
    @Mock
    private EmployerRowMapper employerRowMapper;
    @Mock
    private OfferService offerService;
    @Mock
    private OfferDetailsMapper offerDetailsMapper;
    @Mock
    private OfferRowMapper offerRowMapper;
    @Mock
    private UserController userController;
    @Mock
    private Authentication authentication;
    @Mock
    private Model model;
    @InjectMocks
    private OfferDataController offerDataController;

    @Test
    public void getOffersListTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        OfferSearchCriteria offerSearchCriteria = new OfferSearchCriteria();
        Page<Offer> page = new PageImpl<>(List.of(OfferFixtures.someOffer1()));

        // when
        when(offerService.findAllByCriteria(offerSearchCriteria)).thenReturn(page);
        when(cityService.findAll()).thenReturn(new ArrayList<>());

        ModelAndView response = offerDataController.getOffersList(offerSearchCriteria, model, authentication);

        // then
        assertEquals("offers", response.getViewName());

        verify(offerService).findAllByCriteria(offerSearchCriteria);
        verify(skillService).findAll();
        verify(cityService).findAll();
        verify(employerService).findAll();
    }

    @Test
    public void getOfferDetailsTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Offer offer = OfferFixtures.someOffer1();
        Long offerId = offer.getOfferId();
        OfferDetailsDTO offerDetailsDTO = OfferDetailsDTOFixtures.someOfferDetailsDTO1();

        // when
        when(offerService.findById(offerId)).thenReturn(offer);
        when(offerDetailsMapper.map(offer)).thenReturn(offerDetailsDTO);

        String response = offerDataController.getOfferDetails(offerId, model, authentication);

        // then
        assertEquals("offer_details", response);

        verify(offerService).findById(offerId);
        verify(offerDetailsMapper).map(offer);
    }
}