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
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.search.EmployerSearchCriteria;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(EmployerDataRestController.BASE_PATH)
public class EmployerDataRestController {
    public static final String BASE_PATH = "/api";
    public static final String EMPLOYER_DETAILS = "/employer/{employerId}";
    public static final String EMPLOYERS_LIST = "/employers";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final EmployerDetailsMapper employerDetailsMapper;

    @GetMapping(value = EMPLOYERS_LIST)
    public ResponseEntity<EmployersDTO> getEmployersList(@ModelAttribute EmployerSearchCriteria employerSearchCriteria) {
        Page<Employer> allByCriteria = employerService.findAllByCriteria(employerSearchCriteria);
        Page<EmployerRowDTO> page = allByCriteria.map(employerRowMapper::map);
        List<EmployerRowDTO> content = page.getContent();
        EmployersDTO employersDTO = EmployersDTO.builder().employerRowDTOs(content).build();
        return ResponseEntity.ok(employersDTO);
    }

    @GetMapping(value = EMPLOYER_DETAILS)
    public ResponseEntity<EmployerDetailsDTO> getEmployerDetails(@PathVariable Long employerId) {
        Employer employer = employerService.findById(employerId);
        EmployerDetailsDTO employerDetailsDTO = employerDetailsMapper.map(employer);
        return ResponseEntity.ok(employerDetailsDTO);
    }
}
