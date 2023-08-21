package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.RoleService;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    RoleService roleService;

    public User mapFromDTO(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User.UserBuilder user = User.builder();
        user.userName(userDTO.getUserName());
        user.email(userDTO.getEmail());
        user.password(Utility.encodePassword(userDTO.getPassword()));
        user.isEnabled(userDTO.getIsEnabled());
        user.userUuid(Utility.generateUUID());
        user.role(roleService.findByRole(userDTO.getRole()));
        return user.build();

    }

    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .isEnabled(user.getIsEnabled())
                .role(user.getRole().getRole())
                .build();
    }
}

