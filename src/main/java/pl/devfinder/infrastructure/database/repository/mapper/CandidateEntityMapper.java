package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {

    @Mapping(source = "candidateSkills", target = "candidateSkills", qualifiedByName = "mapCandidateSkills")
    @Mapping(target = "employerId.cityId", ignore = true)
    Candidate mapFromEntity(CandidateEntity candidateEntity);

    @Named("mapCandidateSkills")
    default Set<CandidateSkill> mapCandidateSkills(Set<CandidateSkillEntity> entities) {
        if (Objects.isNull(entities)) {
            return new HashSet<>();
        }
        return entities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "candidateId", ignore = true)
    CandidateSkill mapFromEntity(CandidateSkillEntity entity);

    CandidateEntity mapToEntity(Candidate candidate);
}
