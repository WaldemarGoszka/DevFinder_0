package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.OfferSkill;
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferSkillEntityMapper {

    OfferSkillEntity mapToEntity(OfferSkill offerSkill);
}
