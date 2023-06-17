package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateUUId")
@ToString(of = {"candidateId", "candidateUUId", "firstName", "lastName", "phoneNumber", "createdAt", "status"})
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

    @Column(name = "candidate_uuid", nullable = false)
    private String candidateUUId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_contact", nullable = false)
    private String email_Contact;

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

    @Column(name = "cv_file")
    private String cvFile;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "picture_file")
    private String pictureFile;

    @Column(name = "experience_level")
    private String experienceLevel;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "open_to_relocation")
    private Boolean openToRelocation;

    @Column(name = "open_to_remote_job")
    private Boolean openToRemoteJob;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private EmployerEntity employerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desired_job_city_id")
    private CityEntity desiredJobCityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_city_id")
    private CityEntity residenceCityId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidateId")
    private Set<CandidateSkillEntity> candidateSkills;

}
