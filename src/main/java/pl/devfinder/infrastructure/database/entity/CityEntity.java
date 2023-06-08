package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "cityId")
@ToString(of = {"cityId", "cityName"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cityId")
    private Set<EmployerEntity> employerCities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cityId")
    private Set<OfferEntity> offerCities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "desiredJobCityId")
    private Set<CandidateEntity> candidateDesiredJobCities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "residenceCityId")
    private Set<CandidateEntity> candidateResidenceCities;

}
