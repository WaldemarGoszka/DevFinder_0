package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.entity.OfferEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferEntityMapper {
    @Mapping(target = "cityId.employerCities", ignore = true)
    @Mapping(target = "cityId.offerCities", ignore = true)
    @Mapping(target = "cityId.candidateDesiredJobCities", ignore = true)
    @Mapping(target = "cityId.candidateResidenceCities", ignore = true)
    @Mapping(target = "employerId.cityId", ignore = true)
    @Mapping(target = "offerSkills", ignore = true)
    Offer mapFromEntity(OfferEntity offerEntity);
}
