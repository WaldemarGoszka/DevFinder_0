package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.RoleService;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.RoleEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    RoleService roleService;

    public User map(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );
        user.active(userDTO.getActive());
        user.userUuid(Utility.generateUUID());
        user.roleId(roleService.findByRole(userDTO.getRole()));
        return user.build();

    }
    public UserDTO map(User user){
        return null;
    }
}

