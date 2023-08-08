package pl.devfinder.domain.search;

import lombok.Data;
import org.springframework.data.domain.Sort;
import pl.devfinder.business.management.Keys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CandidateSearchCriteria {
    List<String> status = new ArrayList<>(List.of(Keys.CandidateState.ACTIVE.getName()));
    List<String> experienceLevels = new ArrayList<>();
    List<String> skills = new ArrayList<>();
    Integer minYearsOfExperience;
    String city;
    List<String> openToRemoteJob = new ArrayList<>();
    BigDecimal salaryMax;
    String employer;


    Integer pageNumber = 1;
    Integer pageSize = 5;
    Sort.Direction sortDirection = Sort.Direction.DESC;
    String sortBy = "lastName";

}
