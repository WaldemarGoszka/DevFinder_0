package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferRowDTO {
    String title;
    Integer remoteWork;
    String experienceLevel;
    BigDecimal salaryMin;
    BigDecimal salaryMax;
    OffsetDateTime createdAt;
    Employer employerId;
    City cityId;
//todo dodać odliczanie czasu ile dni oferta jest dostępna,, zamiast salary min i mac dodać Sring salary np 5000 - 6000
}
