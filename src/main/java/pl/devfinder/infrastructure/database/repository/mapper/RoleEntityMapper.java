package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface RoleEntityMapper {
    @Mapping(target = "userId", ignore = true)
    Role mapFromEntity(RoleEntity userEntity);

}
