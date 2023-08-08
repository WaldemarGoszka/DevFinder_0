package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSkill;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferEntityMapper {
//    @Mapping(target = "cityId.employerCities", ignore = true)
//    @Mapping(target = "cityId.offerCities", ignore = true)
//    @Mapping(target = "cityId.candidateDesiredJobCities", ignore = true)
//    @Mapping(target = "cityId.candidateResidenceCities", ignore = true)
    @Mapping(target = "employerId.cityId", ignore = true)
    @Mapping(source = "offerSkills", target = "offerSkills", qualifiedByName = "mapOfferSkills")
    Offer mapFromEntity(OfferEntity offerEntity);

    @Named("mapOfferSkills")
    default Set<OfferSkill> mapOfferSkills(Set<OfferSkillEntity> entities) {
        if(Objects.isNull(entities)){
            return new HashSet<>();
        }
        return entities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }
    @Mapping(target = "offerId", ignore = true)
//    @Mapping(target = "skillId.candidateSkills", ignore = true)
//    @Mapping(target = "skillId.offerSkills", ignore = true)
    OfferSkill mapFromEntity(OfferSkillEntity entity);

    OfferEntity mapToEntity(Offer offer);
}
