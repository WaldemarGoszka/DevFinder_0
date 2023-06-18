package pl.devfinder.business.dao;

import pl.devfinder.domain.Role;

public interface RoleDAO {
    Role findByRole(String role);
}
