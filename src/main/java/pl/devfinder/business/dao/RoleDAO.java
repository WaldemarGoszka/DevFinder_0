package pl.devfinder.business.dao;

import pl.devfinder.domain.Role;

import java.util.Optional;

public interface RoleDAO {
    Optional<Role> findByRole(String role);
}
