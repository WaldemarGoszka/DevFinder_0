package pl.devfinder.api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.api.dto.CandidatesDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.domain.search.CandidateSearchCriteria;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CandidateDataRestController {

    public static final String CANDIDATES_LIST = "/candidates";
    public static final String CANDIDATE_DETAIL = "/candidate/{candidateId}";

    private final CandidateService candidateService;
    private final CandidateDetailsMapper candidateDetailsMapper;
    private final CandidateRowMapper candidateRowMapper;

    @GetMapping(value = CANDIDATES_LIST)
    public ResponseEntity<CandidatesDTO> getCandidatesList(@ModelAttribute CandidateSearchCriteria candidateSearchCriteria) {
        Page<CandidateRowDTO> page = candidateService.findAllByCriteria(candidateSearchCriteria).map(candidateRowMapper::map);
        return ResponseEntity.ok(CandidatesDTO.builder().candidatesDTOs(page.getContent()).build());
    }

    @GetMapping(value = CANDIDATE_DETAIL)
    public ResponseEntity<CandidateDetailsDTO> getCandidateDetails(@PathVariable Long candidateId) {
        return ResponseEntity.ok(candidateDetailsMapper.map(candidateService.findById(candidateId)));
    }


}



