package pl.devfinder.api.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.api.dto.CandidatesDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.util.CandidateDetailsDTOFixtures;
import pl.devfinder.util.CandidateFixtures;
import pl.devfinder.util.CandidateRowDTOFixtures;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateDataRestControllerMockitoTest {
    @Mock
    private CandidateService candidateService;
    @Mock
    private CandidateDetailsMapper candidateDetailsMapper;
    @Mock
    private CandidateRowMapper candidateRowMapper;
    @InjectMocks
    private CandidateDataRestController candidateDataRestController;

    @Test
    void getCandidatesList() {
        //given
        Candidate candidate = CandidateFixtures.someCandidate1();
        CandidateRowDTO candidateRowDTO = CandidateRowDTOFixtures.someCandidateRowDTO1();
        List<Candidate> candidateList = List.of(candidate);
        CandidateSearchCriteria candidateSearchCriteria = new CandidateSearchCriteria();

        // when
        when(candidateService.findAllByCriteria(candidateSearchCriteria)).thenReturn(new PageImpl<>(candidateList));
        when(candidateRowMapper.map(candidate)).thenReturn(candidateRowDTO);

        ResponseEntity<CandidatesDTO> responseEntity = candidateDataRestController.getCandidatesList(candidateSearchCriteria);
        CandidatesDTO candidatesDTO = responseEntity.getBody();

        // then
        verify(candidateService).findAllByCriteria(candidateSearchCriteria);
        verify(candidateRowMapper, times(candidateList.size())).map(candidate);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(candidatesDTO);
        assertEquals(candidateList.size(), candidatesDTO.getCandidatesDTOs().size());
    }

    @Test
    void getCandidateDetails() {
        // given
        Long candidateId = 1L;
        Candidate candidate = CandidateFixtures.someCandidate1();
        CandidateDetailsDTO candidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1();

        // when
        when(candidateService.findById(candidateId)).thenReturn(candidate);
        when(candidateDetailsMapper.map(candidate)).thenReturn(candidateDetailsDTO);

        ResponseEntity<CandidateDetailsDTO> responseEntity = candidateDataRestController.getCandidateDetails(candidateId);
        CandidateDetailsDTO actualCandidateDetailsDTO = responseEntity.getBody();

        // then
        verify(candidateService).findById(candidateId);
        verify(candidateDetailsMapper).map(candidate);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualCandidateDetailsDTO);
        assertEquals(candidateDetailsDTO, actualCandidateDetailsDTO);
    }

    @Test
    void getCandidateDetailsCandidateNotFound() {
        // given
        Long candidateId = 1L;

        // when
        when(candidateService.findById(candidateId)).thenReturn(null);
        ResponseEntity<CandidateDetailsDTO> responseEntity = candidateDataRestController.getCandidateDetails(candidateId);

        // then
        verify(candidateService).findById(candidateId);
        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
    }

}