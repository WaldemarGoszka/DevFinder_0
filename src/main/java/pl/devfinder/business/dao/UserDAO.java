package pl.devfinder.business.dao;

import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.domain.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByEmail(String email);

    void save(User user);

    Optional<User> findByUserName(String userName);
}
