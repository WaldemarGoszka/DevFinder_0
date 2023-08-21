package pl.devfinder.api.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.devfinder.business.JustJoinItApiService;
import pl.devfinder.infrastructure.api_consume.JustJoinItClient;
import pl.devfinder.infrastructure.api_consume.JustJoinItOfferAnalysisResult;
import pl.devfinder.infrastructure.api_consume.JustJoinItOfferAnalysisResultParticularTechnology;
import pl.devfinder.infrastructure.api_consume.JustJoinItOfferDetails;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {JustJoinItApiRestController.class})
@ExtendWith(SpringExtension.class)
class JustJoinItApiRestControllerDiffBlueTest {
    @Autowired
    private JustJoinItApiRestController justJoinItApiRestController;

    @MockBean
    private JustJoinItApiService justJoinItApiService;

    @MockBean
    private JustJoinItClient justJoinItClient;

    /**
     * Method under test: {@link JustJoinItApiRestController#getAnalyticsResult()}
     */
    @Test
    void testGetAnalyticsResult() throws Exception {
        when(justJoinItApiService.getAnalysisResult(Mockito.any()))
                .thenReturn(Optional.of(new JustJoinItOfferAnalysisResult()));
        when(justJoinItClient.getOffers()).thenReturn(Optional.of(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/justjoinit_offers_analysis");
        MockMvcBuilders.standaloneSetup(justJoinItApiRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"totalOffers\":null,\"skillsDistribution\":null,\"experienceLevelDistribution\":null,\"employmentTypeDistribution"
                                        + "\":null,\"mainTechnologyDistribution\":null,\"salaryCurrencyDistribution\":null,\"avgSalaryInPLNCurrencyB2"
                                        + "BContractAccordingToExperienceDistribution\":null,\"avgSalaryInPLNCurrencyPermanentContractAccordingTo"
                                        + "ExperienceDistribution\":null,\"offersWithSalaryRangesExperienceDistribution\":null,\"availableTechnology"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link JustJoinItApiRestController#getAnalyticsResult()}
     */
    @Test
    void testGetAnalyticsResult2() throws Exception {
        when(justJoinItApiService.getAnalysisResult(Mockito.any()))
                .thenReturn(Optional.of(new JustJoinItOfferAnalysisResult()));
        when(justJoinItClient.getOffers()).thenReturn(Optional.of(new ArrayList<>()));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(justJoinItApiRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link JustJoinItApiRestController#getAnalyticsResult(String)}
     */
    @Test
    void testGetAnalyticsResult3() throws Exception {
        when(justJoinItApiService.getAnalysisForParticularTechnology(Mockito.any(),
                Mockito.any())).thenReturn(Optional.of(new JustJoinItOfferAnalysisResultParticularTechnology()));
        when(justJoinItClient.getOffers()).thenReturn(Optional.of(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/justjoinit_offers_analytics_particular_technology/{technology}", "Technology");
        MockMvcBuilders.standaloneSetup(justJoinItApiRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"totalOffers\":null,\"totalOffersInParticularTechnology\":null,\"experienceInParticularDistribution\":null"
                                        + ",\"skillsInInParticularTechnologyDistribution\":null,\"offersWithSalaryRangesInParticularDistribution\""
                                        + ":null,\"avgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution\":null"
                                        + ",\"avgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution\":null"
                                        + ",\"availableTechnology\":null}"));
    }

    /**
     * Method under test: {@link JustJoinItApiRestController#getOfferDetails(String)}
     */
    @Test
    void testGetOfferDetails() throws Exception {
        when(justJoinItClient.getOffer(Mockito.any())).thenReturn(Optional.of(new JustJoinItOfferDetails()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/justjoinit_offers/{offerId}",
                "42");
        MockMvcBuilders.standaloneSetup(justJoinItApiRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"address_text\":null,\"apply_body\":null,\"apply_url\":null,\"banner_url\":null,\"body\":null,\"city\":null,"
                                        + "\"company_logo_url\":null,\"company_name\":null,\"company_size\":null,\"company_url\":null,\"country_code\":null"
                                        + ",\"custom_consent\":null,\"custom_consent_title\":null,\"employment_types\":null,\"experience_level\":null,"
                                        + "\"future_consent\":null,\"future_consent_title\":null,\"id\":null,\"information_clause\":null,\"latitude\":null"
                                        + ",\"longitude\":null,\"marker_icon\":null,\"multilocation\":null,\"open_to_hire_ukrainians\":null,\"published"
                                        + "_at\":null,\"remote\":null,\"remote_interview\":null,\"skills\":null,\"street\":null,\"tags\":null,\"title\":null"
                                        + ",\"video_key\":null,\"video_provider\":null,\"workplace_type\":null}"));
    }

    /**
     * Method under test: {@link JustJoinItApiRestController#getOfferDetails(String)}
     */
    @Test
    void testGetOfferDetails2() throws Exception {
        when(justJoinItClient.getOffers()).thenReturn(Optional.of(new ArrayList<>()));
        when(justJoinItClient.getOffer(Mockito.any())).thenThrow(new RuntimeException("foo"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/justjoinit_offers/{offerId}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(justJoinItApiRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JustJoinItApiRestController#getOffersList()}
     */
    @Test
    void testGetOffersList() throws Exception {
        when(justJoinItClient.getOffers()).thenReturn(Optional.of(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/justjoinit_offers");
        MockMvcBuilders.standaloneSetup(justJoinItApiRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

