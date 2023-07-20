package pl.devfinder.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;
import pl.devfinder.business.management.Keys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CandidateSearchCriteria {
    List<String> experienceLevels = new ArrayList<>();
    List<String> remoteWork = new ArrayList<>();
    BigDecimal salaryMin;
    Integer yearsOfExperience;
    Boolean openToRelocation;
    Boolean openToRemoteJob;

    List<String> status = new ArrayList<>(List.of(Keys.CandidateState.ACTIVE.getState()));

    String city;
    List<String> skills = new ArrayList<>();

    Integer pageNumber = 1;
    Integer pageSize = 5;
    Sort.Direction sortDirection = Sort.Direction.DESC;
    String sortBy = "createdAt";

}
