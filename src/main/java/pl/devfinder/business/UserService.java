package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final CandidateService candidateService;
    private final EmployerService employerService;
    //private final EmailVerificationTokenService emailVerificationTokenService;
    private final EmailVerificationTokenDAO emailVerificationTokenDAO;


    @Transactional
    public Optional<User> findByEmail(String email) {
        log.info("Trying find user by email: [{}]", email);
        Optional<User> user = userDAO.findByEmail(email);
        return user;//.orElseThrow(() -> new NotFoundException("Could not find user by email: [%s]".formatted(email)));
    }

    @Transactional
    public Optional<User> findByUserName(String userName) {
        log.info("Trying find user by userName: [{}]", userName);
        Optional<User> user = userDAO.findByUserName(userName);
        return user;//.orElseThrow(() -> new NotFoundException("Could not find user by userName: [%s]".formatted(userName)));
    }

    @Transactional
    public User save(User user) {
        log.info("Trying save user to database, user: [{}]", user);
        if (user.getRole().getRole().equals(Keys.Role.CANDIDATE.getName())) {
            candidateService.save(user);
        }
        if (user.getRole().getRole().equals(Keys.Role.EMPLOYER.getName())) {
            employerService.save(user);
        }
        return userDAO.save(user);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        log.info("Trying delete user By Id : [{}]", userId);
        Optional<User> user1 = userDAO.findById(userId);
        user1.ifPresent(user -> emailVerificationTokenDAO.deleteByUserId(user.getId()));
        userDAO.deleteById(userId);
    }

    @Transactional
    public void updateUser(String userName, String email, Long id) {
        log.info("Trying update user, email : [{}]", email);
        userDAO.update(userName, email, id);
    }

    public User findById(Long userId) {
        log.info("Trying find user by Id: [{}]", userId);
        Optional<User> user = userDAO.findById(userId);
        return user.orElseThrow(() -> new NotFoundException("Could not find user by Id: [%s]".formatted(userId)));
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }


    // Optional<User> findByUUID(Long id);


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
