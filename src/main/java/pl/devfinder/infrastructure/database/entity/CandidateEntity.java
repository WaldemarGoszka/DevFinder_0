package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateUuid")
@ToString(of = {"candidateId", "candidateUuid", "firstName", "lastName", "phoneNumber", "createdAt", "status"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "candidate_uuid", unique = true, nullable = false)
    private String candidateUuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_contact")
    private String emailContact;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "education")
    private String education;

    @Column(name = "other_skills")
    private String otherSkills;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "foreign_language")
    private String foreignLanguage;

    @Column(name = "cv_filename")
    private String cvFilename;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "photo_filename")
    private String photoFilename;

    @Column(name = "experience_level")
    private String experienceLevel;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "open_to_remote_job")
    private Boolean openToRemoteJob;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private EmployerEntity employerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_city_id")
    private CityEntity residenceCityId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidateId")
    private Set<CandidateSkillEntity> candidateSkills;

}
