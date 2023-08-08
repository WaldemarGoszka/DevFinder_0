package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.SkillDTO;
import pl.devfinder.domain.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillDTO map(Skill skill);

}
