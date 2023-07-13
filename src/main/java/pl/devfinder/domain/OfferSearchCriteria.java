package pl.devfinder.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

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
    String status;
    String city;
    Set<String> skills = new HashSet<>();

    Integer pageNumber = 1;
    Integer pageSize = 3;
    Sort.Direction sortDirection = Sort.Direction.DESC;
    String sortBy = "createdAt";



//    public OfferSearchCriteria(List<String> experienceLevels, Integer remoteWork, BigDecimal salaryMin, String status, String city, Set<String> skills) {
//        this.experienceLevels = new ArrayList<>();
//        this.remoteWork = remoteWork;
//        this.salaryMin = salaryMin;
//        this.status = status;
//        this.city = city;
//        this.skills = new HashSet<>();
//    }
}
