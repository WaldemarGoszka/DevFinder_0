package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "employerUUId")
@ToString(of = {"employerId", "employerUUId", "companyName", "phoneNumber"})
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

    @Column(name = "employer_uuid", nullable = false)
    private String employerUUId;

    @Column(name = "company_name", unique = true, nullable = false)
    private String companyName;

    @Column(name = "email_contact", nullable = false)
    private String email_Contact;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "logo_file")
    private String logoFile;

    @Column(name = "website")
    private String website;

    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity cityId;
}
