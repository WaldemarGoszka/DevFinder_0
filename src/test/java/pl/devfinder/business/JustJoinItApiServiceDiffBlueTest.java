package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.infrastructure.api_consume.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JustJoinItApiService.class})
@ExtendWith(SpringExtension.class)
class JustJoinItApiServiceDiffBlueTest {
    @Autowired
    private JustJoinItApiService justJoinItApiService;

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult() {
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(new ArrayList<>()));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertTrue(getResult.getAvailableTechnology().isEmpty());
        assertEquals(0, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        Map<String, Integer> salaryCurrencyDistribution = getResult.getSalaryCurrencyDistribution();
        assertTrue(salaryCurrencyDistribution.isEmpty());
        Map<String, Integer> offersWithSalaryRangesExperienceDistribution = getResult
                .getOffersWithSalaryRangesExperienceDistribution();
        assertEquals(salaryCurrencyDistribution, offersWithSalaryRangesExperienceDistribution);
        assertTrue(getResult.getMainTechnologyDistribution().isEmpty());
        Map<String, Integer> experienceDistribution = getResult.getExperienceLevelDistribution();
        assertEquals(offersWithSalaryRangesExperienceDistribution, experienceDistribution);
        assertEquals(experienceDistribution, getResult.getEmploymentTypeDistribution());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
    }


    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult3() {
        Optional<List<JustJoinItOffer>> offers = Optional.empty();
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService.getAnalysisResult(offers);
        assertSame(offers, actualAnalysisResult);
        assertFalse(actualAnalysisResult.isPresent());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult4() {
        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        ArrayList<EmploymentType> employment_types = new ArrayList<>();
        ArrayList<Multilocation> multilocation = new ArrayList<>();
        justJoinItOfferList.add(new JustJoinItOffer("42 Main St", "Oxford", "https://example.org/example", "Company name",
                "Company size", "https://example.org/example", "GB", true, employment_types, "Experience level", "42",
                "Latitude", "Longitude", "Marker icon", multilocation, true, "Published at", true, true, new ArrayList<>(),
                "Street", "Dr", "Way of apply", "Workplace type"));
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        Map<String, Integer> salaryCurrencyDistribution = getResult.getSalaryCurrencyDistribution();
        assertTrue(salaryCurrencyDistribution.isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(salaryCurrencyDistribution, getResult.getEmploymentTypeDistribution());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult5() {
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        Map<String, Integer> salaryCurrencyDistribution = getResult.getSalaryCurrencyDistribution();
        assertTrue(salaryCurrencyDistribution.isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(salaryCurrencyDistribution, getResult.getEmploymentTypeDistribution());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
    }


    @Test
    void testGetAnalysisResult10() {
        ArrayList<Skill> skillList = new ArrayList<>();
        skillList.add(new Skill(1L, "Name"));
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(skillList);
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        Map<String, Integer> salaryCurrencyDistribution = getResult.getSalaryCurrencyDistribution();
        assertTrue(salaryCurrencyDistribution.isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(salaryCurrencyDistribution, getResult.getEmploymentTypeDistribution());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
    }


    @Test
    void testGetAnalysisResult12() {
        EmploymentType employmentType = mock(EmploymentType.class);
        BigDecimal from = BigDecimal.valueOf(1L);
        when(employmentType.getSalary()).thenReturn(new Salary(from, BigDecimal.valueOf(1L), "GBP"));
        when(employmentType.getType()).thenReturn("Type");

        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(employmentType);
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        assertEquals(1, getResult.getSalaryCurrencyDistribution().size());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(1, getResult.getEmploymentTypeDistribution().size());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
        verify(employmentType).getType();
        verify(employmentType, atLeast(1)).getSalary();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult13() {
        EmploymentType employmentType = mock(EmploymentType.class);
        when(employmentType.getSalary()).thenReturn(null);
        when(employmentType.getType()).thenReturn("Type");

        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(employmentType);
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        assertTrue(getResult.getSalaryCurrencyDistribution().isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(1, getResult.getEmploymentTypeDistribution().size());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
        verify(employmentType).getType();
        verify(employmentType, atLeast(1)).getSalary();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult14() {
        EmploymentType employmentType = mock(EmploymentType.class);
        BigDecimal from = BigDecimal.valueOf(1L);
        when(employmentType.getSalary())
                .thenReturn(new Salary(from, BigDecimal.valueOf(1L), JustJoinItApiService.MAIN_CURRENCY));
        when(employmentType.getType()).thenReturn("Type");

        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(employmentType);
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        assertEquals(1, getResult.getSalaryCurrencyDistribution().size());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(1, getResult.getEmploymentTypeDistribution().size());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
        verify(employmentType, atLeast(1)).getType();
        verify(employmentType, atLeast(1)).getSalary();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult15() {
        EmploymentType employmentType = mock(EmploymentType.class);
        BigDecimal from = BigDecimal.valueOf(1L);
        when(employmentType.getSalary())
                .thenReturn(new Salary(from, BigDecimal.valueOf(1L), JustJoinItApiService.MAIN_CURRENCY));
        when(employmentType.getType()).thenReturn(JustJoinItApiService.B2B_CONTRACT);

        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(employmentType);
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        assertEquals(1, getResult.getSalaryCurrencyDistribution().size());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(1, getResult.getEmploymentTypeDistribution().size());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        Map<String, BigDecimal> avgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution = getResult
                .getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution();
        assertEquals(1, avgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution.size());
        assertEquals("1",
                avgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution.get("Experience level").toString());
        verify(justJoinItOffer, atLeast(1)).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
        verify(employmentType, atLeast(1)).getType();
        verify(employmentType, atLeast(1)).getSalary();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult16() {
        EmploymentType employmentType = mock(EmploymentType.class);
        BigDecimal from = BigDecimal.valueOf(1L);
        when(employmentType.getSalary())
                .thenReturn(new Salary(from, BigDecimal.valueOf(1L), JustJoinItApiService.MAIN_CURRENCY));
        when(employmentType.getType()).thenReturn(JustJoinItApiService.PERMANENT_CONTRACT);

        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(employmentType);
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(justJoinItOfferList));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        assertEquals(1, getResult.getSalaryCurrencyDistribution().size());
        assertEquals(1, getResult.getOffersWithSalaryRangesExperienceDistribution().size());
        assertEquals(1, getResult.getMainTechnologyDistribution().size());
        assertEquals(1, getResult.getExperienceLevelDistribution().size());
        assertEquals(1, getResult.getEmploymentTypeDistribution().size());
        Map<String, BigDecimal> avgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution = getResult
                .getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution();
        assertEquals(1, avgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution.size());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
        assertEquals("1",
                avgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution.get("Experience level").toString());
        verify(justJoinItOffer, atLeast(1)).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
        verify(employmentType, atLeast(1)).getType();
        verify(employmentType, atLeast(1)).getSalary();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisResult(Optional)}
     */
    @Test
    void testGetAnalysisResult17() {
        Optional<JustJoinItOfferAnalysisResult> actualAnalysisResult = justJoinItApiService
                .getAnalysisResult(Optional.of(new ArrayList<>()));
        assertTrue(actualAnalysisResult.isPresent());
        JustJoinItOfferAnalysisResult getResult = actualAnalysisResult.get();
        assertTrue(getResult.getAvailableTechnology().isEmpty());
        assertEquals(0, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsDistribution().isEmpty());
        Map<String, Integer> salaryCurrencyDistribution = getResult.getSalaryCurrencyDistribution();
        assertTrue(salaryCurrencyDistribution.isEmpty());
        Map<String, Integer> offersWithSalaryRangesExperienceDistribution = getResult
                .getOffersWithSalaryRangesExperienceDistribution();
        assertEquals(salaryCurrencyDistribution, offersWithSalaryRangesExperienceDistribution);
        assertTrue(getResult.getMainTechnologyDistribution().isEmpty());
        Map<String, Integer> experienceLevelDistribution = getResult.getExperienceLevelDistribution();
        assertEquals(offersWithSalaryRangesExperienceDistribution, experienceLevelDistribution);
        assertEquals(experienceLevelDistribution, getResult.getEmploymentTypeDistribution());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution().isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution().isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology() {
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(new ArrayList<>()), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertTrue(getResult.getAvailableTechnology().isEmpty());
        assertEquals(0L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(0, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        Map<String, Integer> offersWithSalaryRangesInParticularDistribution = getResult
                .getOffersWithSalaryRangesInParticularDistribution();
        assertTrue(offersWithSalaryRangesInParticularDistribution.isEmpty());
        assertEquals(offersWithSalaryRangesInParticularDistribution, getResult.getExperienceInParticularDistribution());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology2() {
        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(new JustJoinItOffer());
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(0L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        Map<String, Integer> offersWithSalaryRangesInParticularDistribution = getResult
                .getOffersWithSalaryRangesInParticularDistribution();
        assertTrue(offersWithSalaryRangesInParticularDistribution.isEmpty());
        assertEquals(offersWithSalaryRangesInParticularDistribution, getResult.getExperienceInParticularDistribution());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology3() {
        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(new JustJoinItOffer());
        justJoinItOfferList.add(new JustJoinItOffer());
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(0L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(2, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        Map<String, Integer> offersWithSalaryRangesInParticularDistribution = getResult
                .getOffersWithSalaryRangesInParticularDistribution();
        assertTrue(offersWithSalaryRangesInParticularDistribution.isEmpty());
        assertEquals(offersWithSalaryRangesInParticularDistribution, getResult.getExperienceInParticularDistribution());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology4() {
        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(new JustJoinItOffer());
        justJoinItOfferList.add(new JustJoinItOffer());
        justJoinItOfferList.add(new JustJoinItOffer());
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(0L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(3, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        Map<String, Integer> offersWithSalaryRangesInParticularDistribution = getResult
                .getOffersWithSalaryRangesInParticularDistribution();
        assertTrue(offersWithSalaryRangesInParticularDistribution.isEmpty());
        assertEquals(offersWithSalaryRangesInParticularDistribution, getResult.getExperienceInParticularDistribution());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology5() {
        Optional<List<JustJoinItOffer>> offers = Optional.empty();
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(offers, "Technology Name");
        assertSame(offers, actualAnalysisForParticularTechnology);
        assertFalse(actualAnalysisForParticularTechnology.isPresent());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */

    @Test
    void testGetAnalysisForParticularTechnology7() {
        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        ArrayList<EmploymentType> employment_types = new ArrayList<>();
        ArrayList<Multilocation> multilocation = new ArrayList<>();
        justJoinItOfferList.add(new JustJoinItOffer("42 Main St", "Oxford", "https://example.org/example", "Company name",
                "Company size", "https://example.org/example", "GB", true, employment_types, "Experience level", "42",
                "Latitude", "Longitude", "Marker icon", multilocation, true, "Published at", true, true, new ArrayList<>(),
                "Street", "Dr", "Way of apply", "Workplace type"));
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(0L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        Map<String, Integer> offersWithSalaryRangesInParticularDistribution = getResult
                .getOffersWithSalaryRangesInParticularDistribution();
        assertTrue(offersWithSalaryRangesInParticularDistribution.isEmpty());
        assertEquals(offersWithSalaryRangesInParticularDistribution, getResult.getExperienceInParticularDistribution());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology8() {
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getMarker_icon()).thenReturn("Marker icon");
        when(justJoinItOffer.getEmployment_types()).thenReturn(new ArrayList<>());

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(0L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        Map<String, Integer> offersWithSalaryRangesInParticularDistribution = getResult
                .getOffersWithSalaryRangesInParticularDistribution();
        assertTrue(offersWithSalaryRangesInParticularDistribution.isEmpty());
        assertEquals(offersWithSalaryRangesInParticularDistribution, getResult.getExperienceInParticularDistribution());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */
    @Test
    void testGetAnalysisForParticularTechnology9() {
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");
        when(justJoinItOffer.getMarker_icon()).thenReturn("Technology Name");
        when(justJoinItOffer.getEmployment_types()).thenReturn(new ArrayList<>());

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesInParticularDistribution().size());
        assertEquals(1, getResult.getExperienceInParticularDistribution().size());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
    }


    @Test
    void testGetAnalysisForParticularTechnology12() {
        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(new EmploymentType());
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");
        when(justJoinItOffer.getMarker_icon()).thenReturn("Technology Name");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesInParticularDistribution().size());
        assertEquals(1, getResult.getExperienceInParticularDistribution().size());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
    }


    @Test
    void testGetAnalysisForParticularTechnology15() {
        ArrayList<Skill> skillList = new ArrayList<>();
        skillList.add(new Skill(1L, "Technology Name"));
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(skillList);
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");
        when(justJoinItOffer.getMarker_icon()).thenReturn("Technology Name");
        when(justJoinItOffer.getEmployment_types()).thenReturn(new ArrayList<>());

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertEquals(1, getResult.getSkillsInInParticularTechnologyDistribution().size());
        assertEquals(1, getResult.getOffersWithSalaryRangesInParticularDistribution().size());
        assertEquals(1, getResult.getExperienceInParticularDistribution().size());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
    }

    /**
     * Method under test: {@link JustJoinItApiService#getAnalysisForParticularTechnology(Optional, String)}
     */

    @Test
    void testGetAnalysisForParticularTechnology17() {
        ArrayList<EmploymentType> employmentTypeList = new ArrayList<>();
        employmentTypeList.add(new EmploymentType(new Salary(), "Technology Name"));
        JustJoinItOffer justJoinItOffer = mock(JustJoinItOffer.class);
        when(justJoinItOffer.getSkills()).thenReturn(new ArrayList<>());
        when(justJoinItOffer.getExperience_level()).thenReturn("Experience level");
        when(justJoinItOffer.getMarker_icon()).thenReturn("Technology Name");
        when(justJoinItOffer.getEmployment_types()).thenReturn(employmentTypeList);

        ArrayList<JustJoinItOffer> justJoinItOfferList = new ArrayList<>();
        justJoinItOfferList.add(justJoinItOffer);
        Optional<JustJoinItOfferAnalysisResultParticularTechnology> actualAnalysisForParticularTechnology = justJoinItApiService
                .getAnalysisForParticularTechnology(Optional.of(justJoinItOfferList), "Technology Name");
        assertTrue(actualAnalysisForParticularTechnology.isPresent());
        JustJoinItOfferAnalysisResultParticularTechnology getResult = actualAnalysisForParticularTechnology.get();
        assertEquals(1, getResult.getAvailableTechnology().size());
        assertEquals(1L, getResult.getTotalOffersInParticularTechnology().longValue());
        assertEquals(1, getResult.getTotalOffers().intValue());
        assertTrue(getResult.getSkillsInInParticularTechnologyDistribution().isEmpty());
        assertEquals(1, getResult.getOffersWithSalaryRangesInParticularDistribution().size());
        assertEquals(1, getResult.getExperienceInParticularDistribution().size());
        assertTrue(
                getResult.getAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution()
                        .isEmpty());
        assertTrue(getResult.getAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution()
                .isEmpty());
        verify(justJoinItOffer).getExperience_level();
        verify(justJoinItOffer, atLeast(1)).getMarker_icon();
        verify(justJoinItOffer, atLeast(1)).getEmployment_types();
        verify(justJoinItOffer).getSkills();
    }
}

