package pl.devfinder.domain;

import lombok.*;
import pl.devfinder.business.OfferService;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "employerUUId")
@ToString(of = {"employerId", "employerUUId", "companyName", "emailContact"})
public class Employer {
    Long employerId;
    String employerUUId;
    String companyName;
    String emailContact;
    String phoneNumber;
    String description;
    String logoFile;
    String website;
    Integer numberOfEmployees;
    OffsetDateTime createdAt;
    City cityId;
}
