package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Role;
import pl.devfinder.infrastructure.database.entity.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface RoleEntityMapper {
    @Mapping(target = "userId", ignore = true)
    Role mapFromEntity(RoleEntity userEntity);

}
