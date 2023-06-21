package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.dao.RoleDAO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    private final UserMapper userMapper;

    @Transactional
    public User findByEmail(String email) {
        Optional<User> user = userDAO.findByEmail(email);
//        if (user.isEmpty()) {
//            throw new NotFoundException("Could not find user by email: [%s]".formatted(email));
//        }
        return user.orElseThrow(() -> new NotFoundException("Could not find user by email: [%s]".formatted(email)));
    }
    @Transactional
    public User findByUserName(String userName) {
        Optional<User> user = userDAO.findByUserName(userName);
        return user.orElseThrow(() -> new NotFoundException("Could not find user by userName: [%s]".formatted(userName)));
    }

    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    /////////////////

    public void save(UserDTO userDTO) {
        User user = userMapper.mapFromDTO(userDTO);
//        User user = User.builder()
//                .userName(userDTO.getUserName())
//                .userUuid(Utility.generateUUID())
//                .email(userDTO.getEmail())
//                .password(passwordEncoder.encode(userDTO.getPassword()))
//                .active(userDTO.getActive())
//                .role(roleService.findByRole(userDTO.getRole()))
//                .build();
        userDAO.save(user);
    }


//    @Override
//    public List<UserDto> findAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map((user) -> mapToUserDto(user))
//                .collect(Collectors.toList());
//    }

//    private UserDto mapToUserDto(User user){
//        UserDto userDto = new UserDto();
//        String[] str = user.getName().split(" ");
//        userDto.setFirstName(str[0]);
//        userDto.setLastName(str[1]);
//        userDto.setEmail(user.getEmail());
//        return userDto;
//    }



}
