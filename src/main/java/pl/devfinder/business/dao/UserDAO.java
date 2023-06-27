package pl.devfinder.business.dao;

import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);

    User save(User user);

    Optional<User> findByUserName(String userName);

    void deleteById(Long userId);

    void update(String userName, String email, Long id);

    List<User> findAll();
}
