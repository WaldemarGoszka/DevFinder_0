package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public User findByEmail(String email) {
        Optional<User> user = userDAO.findByEmail(email);
        if (user.isEmpty()) {
            throw new NotFoundException("Could not find user by email: [%s]".formatted(email));
        }
        return user.get();
    }


}
