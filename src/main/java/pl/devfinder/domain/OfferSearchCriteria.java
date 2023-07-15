package pl.devfinder.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;
import pl.devfinder.business.management.Keys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OfferSearchCriteria {
    List<String> experienceLevels = new ArrayList<>();
    List<String> remoteWork = new ArrayList<>();
    BigDecimal salaryMin;
    List<String> salary = new ArrayList<>();
    List<String> status = new ArrayList<>(List.of(Keys.OfferState.ACTIVE.getName()));
    String city;
    String employer;
    List<String> skills = new ArrayList<>();
    Integer pageNumber = 1;
    Integer pageSize = 3;
    Sort.Direction sortDirection = Sort.Direction.DESC;
    String sortBy = "createdAt";

}
