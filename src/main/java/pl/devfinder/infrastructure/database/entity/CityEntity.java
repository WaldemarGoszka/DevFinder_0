package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "city_id")
    private List<EmployerEntity> employerCities;

    @OneToMany(mappedBy = "city_id")
    private List<OfferEntity> offerCities;

    @OneToMany(mappedBy = "desired_job_city_id")
    private List<CandidateEntity> candidateDesiredJobCities;

    @OneToMany(mappedBy = "residence_city_id")
    private List<CandidateEntity> candidateResidenceCities;

}
