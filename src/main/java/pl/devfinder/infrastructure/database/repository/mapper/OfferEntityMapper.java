package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSkills;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferEntityMapper {
    @Mapping(target = "cityId.employerCities", ignore = true)
    @Mapping(target = "cityId.offerCities", ignore = true)
    @Mapping(target = "cityId.candidateDesiredJobCities", ignore = true)
    @Mapping(target = "cityId.candidateResidenceCities", ignore = true)
    @Mapping(target = "employerId.cityId", ignore = true)
    @Mapping(source = "offerSkills", target = "offerSkills", qualifiedByName = "mapOfferSkills")
    Offer mapFromEntity(OfferEntity offerEntity);

    @Named("mapOfferSkills")
    default Set<OfferSkills> mapOfferSkills(Set<OfferSkillEntity> entities) {
        return entities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }
    @Mapping(target = "offerId", ignore = true)
    @Mapping(target = "skillId.candidateSkills", ignore = true)
    @Mapping(target = "skillId.offerSkills", ignore = true)
    OfferSkills mapFromEntity(OfferSkillEntity entity);
}
