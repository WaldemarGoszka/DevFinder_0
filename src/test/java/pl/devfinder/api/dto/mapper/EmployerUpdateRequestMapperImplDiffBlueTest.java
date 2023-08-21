package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.api.dto.EmployerUpdateRequestDTO;
import pl.devfinder.domain.EmployerUpdateRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployerUpdateRequestMapperImpl.class})
@ExtendWith(SpringExtension.class)
class EmployerUpdateRequestMapperImplDiffBlueTest {
    @Autowired
    private EmployerUpdateRequestMapperImpl employerUpdateRequestMapperImpl;

    /**
     * Method under test: {@link EmployerUpdateRequestMapperImpl#map(EmployerUpdateRequestDTO)}
     */
    @Test
    void testMap() {
        EmployerUpdateRequest actualMapResult = employerUpdateRequestMapperImpl.map(new EmployerUpdateRequestDTO());
        assertNull(actualMapResult.getAmountOfAvailableOffers());
        assertNull(actualMapResult.getWebsite());
        assertNull(actualMapResult.getPhoneNumber());
        assertNull(actualMapResult.getNumberOfEmployees());
        assertNull(actualMapResult.getLogoFilename());
        assertNull(actualMapResult.getFileLogo());
        assertNull(actualMapResult.getEmployerUuid());
        assertNull(actualMapResult.getEmployerId());
        assertNull(actualMapResult.getEmailContact());
        assertNull(actualMapResult.getDescription());
        assertNull(actualMapResult.getCreatedAt());
        assertNull(actualMapResult.getCompanyName());
        assertNull(actualMapResult.getCityName());
    }

    /**
     * Method under test: {@link EmployerUpdateRequestMapperImpl#map(EmployerUpdateRequestDTO)}
     */
    @Test
    void testMap2() {
        assertNull(employerUpdateRequestMapperImpl.map(null));
    }

    /**
     * Method under test: {@link EmployerUpdateRequestMapperImpl#map(EmployerUpdateRequestDTO)}
     */
    @Test
    void testMap3() throws IOException {
        EmployerUpdateRequestDTO employerUpdateRequestDTO = mock(EmployerUpdateRequestDTO.class);
        when(employerUpdateRequestDTO.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerUpdateRequestDTO.getNumberOfEmployees()).thenReturn(10);
        when(employerUpdateRequestDTO.getEmployerId()).thenReturn(1L);
        when(employerUpdateRequestDTO.getCityName()).thenReturn("Oxford");
        when(employerUpdateRequestDTO.getCompanyName()).thenReturn("Company Name");
        when(employerUpdateRequestDTO.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerUpdateRequestDTO.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerUpdateRequestDTO.getEmployerUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(employerUpdateRequestDTO.getLogoFilename()).thenReturn("foo.txt");
        when(employerUpdateRequestDTO.getPhoneNumber()).thenReturn("6625550144");
        when(employerUpdateRequestDTO.getWebsite()).thenReturn("Website");
        when(employerUpdateRequestDTO.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("Name",
                new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));

        when(employerUpdateRequestDTO.getFileLogo()).thenReturn(mockMultipartFile);
        EmployerUpdateRequest actualMapResult = employerUpdateRequestMapperImpl.map(employerUpdateRequestDTO);
        assertEquals(10, actualMapResult.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", actualMapResult.getWebsite());
        assertEquals("6625550144", actualMapResult.getPhoneNumber());
        assertEquals(10, actualMapResult.getNumberOfEmployees().intValue());
        assertEquals("foo.txt", actualMapResult.getLogoFilename());
        assertSame(mockMultipartFile, actualMapResult.getFileLogo());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapResult.getEmployerUuid());
        assertEquals(1L, actualMapResult.getEmployerId().longValue());
        assertEquals("Oxford", actualMapResult.getCityName());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertEquals("The characteristics of someone or something", actualMapResult.getDescription());
        assertEquals("Company Name", actualMapResult.getCompanyName());
        assertEquals("jane.doe@example.org", actualMapResult.getEmailContact());
        verify(employerUpdateRequestDTO).getAmountOfAvailableOffers();
        verify(employerUpdateRequestDTO).getNumberOfEmployees();
        verify(employerUpdateRequestDTO).getEmployerId();
        verify(employerUpdateRequestDTO).getCityName();
        verify(employerUpdateRequestDTO).getCompanyName();
        verify(employerUpdateRequestDTO).getDescription();
        verify(employerUpdateRequestDTO).getEmailContact();
        verify(employerUpdateRequestDTO).getEmployerUuid();
        verify(employerUpdateRequestDTO).getLogoFilename();
        verify(employerUpdateRequestDTO).getPhoneNumber();
        verify(employerUpdateRequestDTO).getWebsite();
        verify(employerUpdateRequestDTO).getCreatedAt();
        verify(employerUpdateRequestDTO).getFileLogo();
    }
}

