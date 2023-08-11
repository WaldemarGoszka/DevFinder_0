package pl.devfinder.api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.api.dto.EmployersDTO;
import pl.devfinder.api.dto.mapper.EmployerDetailsMapper;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.business.EmployerService;
import pl.devfinder.domain.search.EmployerSearchCriteria;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployerDataRestController {
    public static final String EMPLOYER_DETAILS = "/employer/{employerId}";
    public static final String EMPLOYERS_LIST = "/employers";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final EmployerDetailsMapper employerDetailsMapper;

    @GetMapping(value = EMPLOYERS_LIST)
    public ResponseEntity<EmployersDTO> getEmployersList(@ModelAttribute EmployerSearchCriteria employerSearchCriteria) {
        Page<EmployerRowDTO> page = employerService.findAllByCriteria(employerSearchCriteria).map(employerRowMapper::map);
        return ResponseEntity.ok(EmployersDTO.builder().employerRowDTOs(page.getContent()).build());
    }

    @GetMapping(value = EMPLOYER_DETAILS)
    public ResponseEntity<EmployerDetailsDTO> getEmployerDetails(@PathVariable Long employerId) {
        return ResponseEntity.ok(employerDetailsMapper.map(employerService.findById(employerId)));
    }
}
