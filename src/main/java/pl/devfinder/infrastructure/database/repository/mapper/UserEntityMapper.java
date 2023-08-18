package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(target = "roleId.userId", ignore = true)
    @Mapping(source = "roleId", target = "role", qualifiedByName = "mapUserRoleFromEntity")
    User mapFromEntity(UserEntity userEntity);
    @Named(value = "mapUserRoleFromEntity")
    default Role mapUserRoleFromEntity(RoleEntity roleEntity){
        return mapFromEntity(roleEntity);
    }
     @Mapping(target = "userId", ignore = true)
    Role mapFromEntity(RoleEntity roleEntity);

    @Mapping(source = "role", target = "roleId", qualifiedByName = "mapUserRole")
    UserEntity mapToEntity(User user);

    @Named(value = "mapUserRole")
    default RoleEntity mapUserRole(Role role) {
        return mapToEntity(role);
    }
    RoleEntity mapToEntity(Role role);

}
