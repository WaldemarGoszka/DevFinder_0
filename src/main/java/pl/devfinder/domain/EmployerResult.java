package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "companyName")
@ToString(of = {"companyName"})
public class EmployerResult {

    String companyName;
    String logoFile;
    String website;
    Integer numberOfEmployees;
    City cityId;
    Integer NumberOfOffers;
}

