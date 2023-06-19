package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.RoleDAO;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.infrastructure.database.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {
    RoleDAO roleDAO;
    public Role findByRole(String role) {
        return roleDAO.findByRole(role)
                .orElseThrow(() -> new NotFoundException("Could not find role: [%s]".formatted(role)));
    }

}
