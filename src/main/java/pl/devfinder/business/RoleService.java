package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.RoleDAO;
import pl.devfinder.domain.Role;
import pl.devfinder.infrastructure.database.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService implements RoleDAO {
    RoleRepository roleRepository;
    public Role findByRole(String role) {

        return roleRepository.findByRole(role);
    }

}
