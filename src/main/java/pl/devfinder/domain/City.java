package pl.devfinder.domain;

import lombok.*;
import java.util.Set;
@With
@Value
@Builder
@EqualsAndHashCode(of = "cityName")
@ToString(of = {"cityId", "cityName"})
public class City {
    Long cityId;
    String cityName;
    Set<Employer> employerCities;
    Set<Offer> offerCities;
    Set<Candidate> candidateDesiredJobCities;
    Set<Candidate> candidateResidenceCities;

}
