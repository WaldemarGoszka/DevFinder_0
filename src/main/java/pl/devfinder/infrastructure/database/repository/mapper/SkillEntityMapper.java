package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Skill;
import pl.devfinder.infrastructure.database.entity.SkillEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SkillEntityMapper {

    Skill mapFromEntity(SkillEntity skillEntity);
}
