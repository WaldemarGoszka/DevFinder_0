package pl.devfinder.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;
import pl.devfinder.business.management.Keys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployerSearchCriteria {

    String city;
    String companyName;
    List<String> skillsInOffers = new ArrayList<>();
    Integer amountOffers;
    Integer numberOfEmployees;


    Integer pageNumber = 1;
    Integer pageSize = 5;
    Sort.Direction sortDirection = Sort.Direction.ASC;
    String sortBy = "companyName";

}
