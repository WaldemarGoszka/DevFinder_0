package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(target = "roleId.userId", ignore = true)
    User mapFromEntity(UserEntity userEntity);
    UserEntity mapToEntity(User user);
    //Ciekawe czy nie będzie trzeba dodć oddzielnego mappera do RoleEntity
}
