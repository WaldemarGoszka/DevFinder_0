package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.api.dto.OfferUpdateRequestDTO;
import pl.devfinder.domain.OfferUpdateRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OfferUpdateRequestMapperImpl.class})
@ExtendWith(SpringExtension.class)
class OfferUpdateRequestMapperImplDiffBlueTest {
    @Autowired
    private OfferUpdateRequestMapperImpl offerUpdateRequestMapperImpl;

    /**
     * Method under test: {@link OfferUpdateRequestMapperImpl#map(OfferUpdateRequestDTO)}
     */
    @Test
    void testMap() {
        OfferUpdateRequest actualMapResult = offerUpdateRequestMapperImpl.map(new OfferUpdateRequestDTO());
        assertNull(actualMapResult.getBenefits());
        assertNull(actualMapResult.getYearsOfExperience());
        assertNull(actualMapResult.getTitle());
        assertNull(actualMapResult.getStatus());
        assertNull(actualMapResult.getSalaryMin());
        assertNull(actualMapResult.getSalaryMax());
        assertNull(actualMapResult.getRemoteWork());
        assertNull(actualMapResult.getOtherSkills());
        assertNull(actualMapResult.getOfferUuid());
        assertNull(actualMapResult.getOfferSkillsNames());
        assertNull(actualMapResult.getOfferId());
        assertNull(actualMapResult.getExperienceLevel());
        assertNull(actualMapResult.getEmployerId());
        assertNull(actualMapResult.getDescription());
        assertNull(actualMapResult.getCreatedAt());
        assertNull(actualMapResult.getCityName());
        assertNull(actualMapResult.getCityId());
    }

    /**
     * Method under test: {@link OfferUpdateRequestMapperImpl#map(OfferUpdateRequestDTO)}
     */
    @Test
    void testMap2() {
        assertNull(offerUpdateRequestMapperImpl.map(null));
    }

    /**
     * Method under test: {@link OfferUpdateRequestMapperImpl#map(OfferUpdateRequestDTO)}
     */
    @Test
    void testMap3() {
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        BigDecimal salaryMax = BigDecimal.valueOf(1L);
        OffsetDateTime createdAt = OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        OfferUpdateRequest actualMapResult = offerUpdateRequestMapperImpl
                .map(new OfferUpdateRequestDTO(1L, "01234567-89AB-CDEF-FEDC-BA9876543210", "Dr",
                        "The characteristics of someone or something", "Other Skills", 1, "Experience Level", 1, salaryMin,
                        salaryMax, createdAt, "Benefits", "Status", null, "Oxford", new HashSet<>()));
        assertEquals("Benefits", actualMapResult.getBenefits());
        assertEquals(1, actualMapResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapResult.getTitle());
        assertEquals("Status", actualMapResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapResult.getSalaryMax());
        assertNull(actualMapResult.getCityId());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertEquals(1L, actualMapResult.getOfferId().longValue());
        assertEquals(1, actualMapResult.getRemoteWork().intValue());
        assertEquals("Other Skills", actualMapResult.getOtherSkills());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapResult.getOfferUuid());
        assertTrue(actualMapResult.getOfferSkillsNames().isEmpty());
        assertEquals("The characteristics of someone or something", actualMapResult.getDescription());
        assertEquals("Experience Level", actualMapResult.getExperienceLevel());
        assertNull(actualMapResult.getEmployerId());
        assertEquals("Oxford", actualMapResult.getCityName());
        assertEquals("1", salaryMin2.toString());
    }

    /**
     * Method under test: {@link OfferUpdateRequestMapperImpl#map(OfferUpdateRequestDTO)}
     */
    @Test
    void testMap4() {
        OfferUpdateRequestDTO offerUpdateRequestDTO = mock(OfferUpdateRequestDTO.class);
        when(offerUpdateRequestDTO.getRemoteWork()).thenReturn(1);
        when(offerUpdateRequestDTO.getYearsOfExperience()).thenReturn(1);
        when(offerUpdateRequestDTO.getOfferId()).thenReturn(1L);
        when(offerUpdateRequestDTO.getBenefits()).thenReturn("Benefits");
        when(offerUpdateRequestDTO.getCityName()).thenReturn("Oxford");
        when(offerUpdateRequestDTO.getDescription()).thenReturn("The characteristics of someone or something");
        when(offerUpdateRequestDTO.getExperienceLevel()).thenReturn("Experience Level");
        when(offerUpdateRequestDTO.getOfferUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(offerUpdateRequestDTO.getOtherSkills()).thenReturn("Other Skills");
        when(offerUpdateRequestDTO.getStatus()).thenReturn("Status");
        when(offerUpdateRequestDTO.getTitle()).thenReturn("Dr");
        when(offerUpdateRequestDTO.getSalaryMax()).thenReturn(BigDecimal.valueOf(1L));
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(offerUpdateRequestDTO.getSalaryMin()).thenReturn(valueOfResult);
        when(offerUpdateRequestDTO.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(offerUpdateRequestDTO.getOfferSkillsNames()).thenReturn(new HashSet<>());
        when(offerUpdateRequestDTO.getEmployerId()).thenReturn(null);
        OfferUpdateRequest actualMapResult = offerUpdateRequestMapperImpl.map(offerUpdateRequestDTO);
        assertEquals("Benefits", actualMapResult.getBenefits());
        assertEquals(1, actualMapResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapResult.getTitle());
        assertEquals("Status", actualMapResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin = actualMapResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin);
        assertSame(salaryMin, actualMapResult.getSalaryMax());
        assertNull(actualMapResult.getCityId());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertEquals(1L, actualMapResult.getOfferId().longValue());
        assertEquals(1, actualMapResult.getRemoteWork().intValue());
        assertEquals("Other Skills", actualMapResult.getOtherSkills());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapResult.getOfferUuid());
        assertTrue(actualMapResult.getOfferSkillsNames().isEmpty());
        assertEquals("The characteristics of someone or something", actualMapResult.getDescription());
        assertEquals("Experience Level", actualMapResult.getExperienceLevel());
        assertNull(actualMapResult.getEmployerId());
        assertEquals("Oxford", actualMapResult.getCityName());
        assertEquals("1", salaryMin.toString());
        verify(offerUpdateRequestDTO).getRemoteWork();
        verify(offerUpdateRequestDTO).getYearsOfExperience();
        verify(offerUpdateRequestDTO).getOfferId();
        verify(offerUpdateRequestDTO).getBenefits();
        verify(offerUpdateRequestDTO).getCityName();
        verify(offerUpdateRequestDTO).getDescription();
        verify(offerUpdateRequestDTO).getExperienceLevel();
        verify(offerUpdateRequestDTO).getOfferUuid();
        verify(offerUpdateRequestDTO).getOtherSkills();
        verify(offerUpdateRequestDTO).getStatus();
        verify(offerUpdateRequestDTO).getTitle();
        verify(offerUpdateRequestDTO).getSalaryMax();
        verify(offerUpdateRequestDTO).getSalaryMin();
        verify(offerUpdateRequestDTO).getCreatedAt();
        verify(offerUpdateRequestDTO).getOfferSkillsNames();
        verify(offerUpdateRequestDTO).getEmployerId();
    }
}

