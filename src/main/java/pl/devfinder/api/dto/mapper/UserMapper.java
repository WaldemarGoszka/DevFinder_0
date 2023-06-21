package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.RoleService;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.RoleEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    RoleService roleService;

    public User mapFromDTO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userDTO.getEmail() );
        user.password(Utility.encodePassword(userDTO.getPassword()));
        user.active(userDTO.getActive());
        user.userUuid(Utility.generateUUID());
        user.role(roleService.findByRole(userDTO.getRole()));
        return user.build();

    }
    public UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .role(user.getRole().getRole())
                .build();
    }
}

