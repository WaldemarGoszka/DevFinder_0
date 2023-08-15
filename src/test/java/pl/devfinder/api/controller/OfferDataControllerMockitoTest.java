package pl.devfinder.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.mapper.OfferDetailsMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.OfferSearchCriteria;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OfferDataControllerMockitoTest {
    @Mock
    private OfferService offerService;

    @Mock
    private UserService userService;

    @Mock
    private OfferRowMapper offerRowMapper;
    @Mock
    private OfferDetailsMapper offerDetailsMapper;

    @Mock
    private UserController userController;
    @Mock
    Authentication authentication;
    @Mock
    Model model;
    @Mock
    OfferSearchCriteria offerSearchCriteria;
    @InjectMocks
    private OfferDataController offerDataController;

//    @Test
//    void testGetOffersList() {
//        // When
//        ModelAndView modelAndView = offerDataController.getOffersList(offerSearchCriteria,model,authentication);
//
//        // Then
//        assertEquals("offers", modelAndView.getViewName());
//    }

    @Test
    void testGetOfferDetails() {
        // When
        String viewName = offerDataController.getOfferDetails(1L,model,authentication);

        // Then
        assertEquals("offer_details", viewName);
    }
}