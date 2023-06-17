package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateSkillEntityMapper {

    CandidateSkill mapFromEntity(CandidateSkillEntity candidateSkillEntity);


}
