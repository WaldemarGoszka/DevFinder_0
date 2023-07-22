package pl.devfinder.domain.search;

import lombok.Data;
import org.springframework.data.domain.Sort;
import pl.devfinder.business.management.Keys;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployerSearchCriteria {

    String city;
    String companyName;
    List<String> skillsInOffers = new ArrayList<>();
    Integer amountOfAvailableOffers;
    Integer numberOfEmployees;
    List<String> jobOffersStatus = new ArrayList<>();


    Integer pageNumber = 1;
    Integer pageSize = 5;
    Sort.Direction sortDirection = Sort.Direction.DESC;
    String sortBy = Keys.EmployerSortBy.amountOfAvailableOffers.getName();

}
