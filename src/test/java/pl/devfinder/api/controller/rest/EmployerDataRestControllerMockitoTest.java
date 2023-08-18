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
import org.springframework.security.core.Authentication;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.api.dto.EmployersDTO;
import pl.devfinder.api.dto.mapper.EmployerDetailsMapper;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.business.EmployerService;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.util.EmployerDetailsDTOFixtures;
import pl.devfinder.util.EmployerFixtures;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerDataRestControllerMockitoTest {

    @Mock
    private EmployerService employerService;
    @Mock
    private EmployerRowMapper employerRowMapper;
    @Mock
    private EmployerDetailsMapper employerDetailsMapper;


    @InjectMocks
    private EmployerDataRestController employerDataRestController;

    @Test
    void getEmployersList() {
        // given
        EmployerSearchCriteria employerSearchCriteria = new EmployerSearchCriteria();
        Employer employer = EmployerFixtures.someEmployer1();
        List<Employer> employerList = List.of(employer);
        Page<Employer> page = new PageImpl<>(employerList);
        EmployerRowDTO employerRowDTO = new EmployerRowDTO();
        List<EmployerRowDTO> employerRowDTOs = Collections.singletonList(employerRowDTO);
        EmployersDTO expectedEmployersDTO = EmployersDTO.builder().employerRowDTOs(employerRowDTOs).build();

        // when
        when(employerService.findAllByCriteria(employerSearchCriteria)).thenReturn(page);
        when(employerRowMapper.map(employer)).thenReturn(employerRowDTO);

        ResponseEntity<EmployersDTO> responseEntity = employerDataRestController.getEmployersList(employerSearchCriteria);
        EmployersDTO actualEmployersDTO = responseEntity.getBody();

        // then
        verify(employerService).findAllByCriteria(employerSearchCriteria);
        verify(employerRowMapper).map(employer);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualEmployersDTO);
        assertEquals(expectedEmployersDTO, actualEmployersDTO);
    }

    @Test
    void getEmployerDetails() {
        // given
        Employer employer = EmployerFixtures.someEmployer1();
        Long employerId = employer.getEmployerId();
        EmployerDetailsDTO expectedEmployerDetailsDTO = EmployerDetailsDTOFixtures.someEmployerDetailsDTO1();

        // when
        when(employerService.findById(employerId)).thenReturn(employer);
        when(employerDetailsMapper.map(employer)).thenReturn(expectedEmployerDetailsDTO);

        ResponseEntity<EmployerDetailsDTO> responseEntity = employerDataRestController.getEmployerDetails(employerId);
        EmployerDetailsDTO actualEmployerDetailsDTO = responseEntity.getBody();

        // then
        verify(employerService).findById(employerId);
        verify(employerDetailsMapper).map(employer);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualEmployerDetailsDTO);
        assertEquals(expectedEmployerDetailsDTO, actualEmployerDetailsDTO);
    }

    @Test
    void getEmployerDetailsEmployerNotFound() {
        // given
        Long employerId = 1L;

        // when
        when(employerService.findById(employerId)).thenReturn(null);

        ResponseEntity<EmployerDetailsDTO> responseEntity = employerDataRestController.getEmployerDetails(employerId);

        // then
        verify(employerService).findById(employerId);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
    }
}