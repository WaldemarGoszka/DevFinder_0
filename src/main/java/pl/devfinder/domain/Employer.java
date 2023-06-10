package pl.devfinder.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "employerUUId")
@ToString(of = {"employerId", "employerUUId", "companyName", "email_Contact"})
public class Employer {
     Long employerId;
     String employerUUId;
     String companyName;
     String email_Contact;
     String phoneNumber;
     String description;
     String logoFile;
     String website;
     Integer numberOfEmployees;
     OffsetDateTime createdAt;
     City cityId;
}
