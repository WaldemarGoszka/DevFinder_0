package pl.devfinder.business.dao;

import pl.devfinder.domain.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByEmail(String email);
}
