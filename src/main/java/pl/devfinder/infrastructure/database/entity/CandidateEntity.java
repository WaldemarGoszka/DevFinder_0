package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateId")
@ToString(of = {"candidateId", "firstName", "lastName", "phoneNumber", "createdAt", "status"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
public class CandidateEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "candiate_id")
        private Long candidateId;

        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name", nullable = false)
        private String lastName;

        @Column(name = "phone_number", nullable = false)
        private String phoneNumber;

        @Column(name = "created_at", nullable = false)
        private OffsetDateTime createdAt;

        @Column(name = "status", nullable = false)
        private String status;

        @Column(name = "education")
        private String education;

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

        @Column(name = "experience")
        private String experience;

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
        private EmployerEntity employer;

        @ManyToOne
        @JoinColumn(name = "desired_job_city_id")
        private CityEntity desiredJobCity;

        @ManyToOne
        @JoinColumn(name = "residence_city_id")
        private CityEntity residenceCity;

}
