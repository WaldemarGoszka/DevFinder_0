package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.*;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.Skill;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;
import pl.devfinder.infrastructure.database.entity.SkillEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {
    @Mappings({
//            @Mapping(target = "candidateSkills.candidateSkillId", ignore = true),
//            @Mapping(target = "candidateSkills.candidateId", ignore = true),
//            @Mapping(target = "candidateSkills.skillId", ignore = true),
            @Mapping(source = "candidateSkills", target = "candidateSkills", qualifiedByName = "mapCandidateSkills"),

            @Mapping(target = "desiredJobCityId.employerCities", ignore = true),
            @Mapping(target = "desiredJobCityId.offerCities", ignore = true),
            @Mapping(target = "desiredJobCityId.candidateDesiredJobCities", ignore = true),
            @Mapping(target = "desiredJobCityId.candidateResidenceCities", ignore = true),
            @Mapping(target = "residenceCityId.employerCities", ignore = true),
            @Mapping(target = "residenceCityId.offerCities", ignore = true),
            @Mapping(target = "residenceCityId.candidateDesiredJobCities", ignore = true),
            @Mapping(target = "residenceCityId.candidateResidenceCities", ignore = true),
            @Mapping(target = "employerId.cityId", ignore = true),
    })
    Candidate mapFromEntity(CandidateEntity candidateEntity);
    @Named("mapCandidateSkills")
    default Set<CandidateSkill> mapCandidateSkills(Set<CandidateSkillEntity> entities) {
        return entities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "candidateSkills", ignore = true)
    @Mapping(target = "offerSkills", ignore = true)
    CandidateSkill mapFromEntity(CandidateSkillEntity entity);
}
