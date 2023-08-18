package pl.devfinder.api.controller.rest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.api.dto.CandidatesDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.util.CandidateFixtures;
import pl.devfinder.util.CandidateRowDTOFixtures;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CandidateDataRestController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CandidateDataRestControllerWebMvcTest {

    private final MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;
    @MockBean
    private CandidateDetailsMapper candidateDetailsMapper;
    @MockBean
    private CandidateRowMapper candidateRowMapper;

    @Test
    void shouldReturnCandidatesList() throws Exception {
        // given
        CandidateSearchCriteria searchCriteria = new CandidateSearchCriteria();
        Candidate candidate = CandidateFixtures.someCandidate1();
        Page<Candidate> page = new PageImpl<>(List.of(candidate));
        CandidateRowDTO candidateRowDTO = CandidateRowDTOFixtures.someCandidateRowDTO1();
        CandidatesDTO candidatesDTO = CandidatesDTO.builder().candidatesDTOs(List.of(candidateRowDTO)).build();

        // when
        when(candidateService.findAllByCriteria(searchCriteria)).thenReturn(page);
        when(candidateRowMapper.map(candidate)).thenReturn(candidateRowDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CandidateDataRestController.BASE_PATH + CandidateDataRestController.CANDIDATES_LIST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(candidatesDTO)));

        verify(candidateService).findAllByCriteria(searchCriteria);
    }
}