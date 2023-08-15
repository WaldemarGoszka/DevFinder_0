package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final EmailVerificationTokenDAO emailVerificationTokenDAO;
    private final CandidateService candidateService;
    private final EmployerService employerService;


    @Transactional
    public Optional<User> findByEmail(String email) {
        log.info("Process find user by email: [{}]", email);
        return userDAO.findByEmail(email);
    }

    @Transactional
    public Optional<User> findByUserName(String userName) {
        log.info("Process find user by userName: [{}]", userName);
        return userDAO.findByUserName(userName);
    }

    @Transactional
    public User save(User user) {
        log.info("Process save user to database, user: [{}]", user);
        return userDAO.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        log.info("Process delete user By Id : [{}]", user.getId());
        emailVerificationTokenDAO.deleteByUserId(user.getId());
        if (user.getRole().getRole().equals(Keys.Role.CANDIDATE.getName())) {
            Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
            candidate.ifPresent(value -> candidateService.deleteCandidateProfile(value.getCandidateId()));
        }
        if (user.getRole().getRole().equals(Keys.Role.EMPLOYER.getName())) {
            Optional<Employer> employer = employerService.findByEmployerUuid(user.getUserUuid());
            employer.ifPresent(value -> employerService.deleteEmployerProfile(value.getEmployerId()));
        }
        userDAO.deleteById(user.getId());
    }

    @Transactional
    public void updateUser(String userName, String email, Long id) {
        log.info("Process update user, email : [{}]", email);
        userDAO.update(userName, email, id);
    }

    public User findById(Long userId) {
        log.info("Process find user by Id: [{}]", userId);
        Optional<User> user = userDAO.findById(userId);
        return user.orElseThrow(() -> new NotFoundException("Could not find user by Id: [%s]".formatted(userId)));
    }

    public List<User> findAll() {
        log.info("Process find all users");

        return userDAO.findAll();
    }
}
