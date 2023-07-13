package pl.devfinder.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OfferSearchCriteria {
    List<String> experienceLevels = new ArrayList<>();
    Integer remoteWork;
    //    Integer yearsOfExperience;
    BigDecimal salaryMin;
    //    BigDecimal salaryMax;
    String status;
    String city;
    Set<String> skills = new HashSet<>();

//    public OfferSearchCriteria(List<String> experienceLevels, Integer remoteWork, BigDecimal salaryMin, String status, String city, Set<String> skills) {
//        this.experienceLevels = new ArrayList<>();
//        this.remoteWork = remoteWork;
//        this.salaryMin = salaryMin;
//        this.status = status;
//        this.city = city;
//        this.skills = new HashSet<>();
//    }
}
