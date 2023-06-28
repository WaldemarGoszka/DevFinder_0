package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.*;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(target = "roleId.userId", ignore = true)
    User mapFromEntity(UserEntity userEntity);

    @Mapping(source = "role", target = "roleId", qualifiedByName = "mapUserRole")
    UserEntity mapToEntity(User user);

    @Named(value = "mapUserRole")
    default RoleEntity mapUserRole(Role role) {
        return mapToEntity(role);
    }
    //@Mapping(target = "userId", ignore = true)
    RoleEntity mapToEntity(Role role);

}
