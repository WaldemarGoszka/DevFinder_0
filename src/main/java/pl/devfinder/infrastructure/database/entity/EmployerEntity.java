package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "employerUuid")
@ToString(of = {"employerId", "employerUuid", "companyName", "phoneNumber"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employer")
public class EmployerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private Long employerId;

    @Column(name = "employer_uuid", unique = true, nullable = false)
    private String employerUuid;

    @Column(name = "company_name", unique = true, nullable = false)
    private String companyName;

    @Column(name = "email_contact")
    private String emailContact;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "logo_filename")
    private String logoFilename;

    @Column(name = "website")
    private String website;

    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @Column(name = "amount_of_available_offers")
    private Integer amountOfAvailableOffers;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity cityId;
}
