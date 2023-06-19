package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;

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


}
