package pl.devfinder.api.controller.rest;

import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.util.CandidateDetailsDTOFixtures;
import pl.devfinder.util.CandidateFixtures;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CandidateDataRestController.class)
@AutoConfigureMockMvc(addFilters = false)
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
    void shouldReturnCandidateDetails() throws Exception {
        // given
        Candidate candidate = CandidateFixtures.someCandidate1();
        Long candidateId = candidate.getCandidateId();
        CandidateDetailsDTO candidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1();

        // when
        when(candidateService.findById(candidateId)).thenReturn(candidate);
        when(candidateDetailsMapper.map(candidate)).thenReturn(candidateDetailsDTO);

        // then
        String endpoint = CandidateDataRestController.BASE_PATH + CandidateDataRestController.CANDIDATE_DETAIL;
        mockMvc.perform(get(endpoint, candidateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.candidateId", Matchers.is(candidateDetailsDTO.getCandidateId().intValue())))
                .andExpect(jsonPath("$.candidateUuid", Matchers.is(candidateDetailsDTO.getCandidateUuid())))
                .andExpect(jsonPath("$.firstName", Matchers.is(candidateDetailsDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", Matchers.is(candidateDetailsDTO.getLastName())))
                .andExpect(jsonPath("$.emailContact", Matchers.is(candidateDetailsDTO.getEmailContact())))
                .andExpect(jsonPath("$.phoneNumber", Matchers.is(candidateDetailsDTO.getPhoneNumber())));

        verify(candidateService).findById(candidateId);
    }
}