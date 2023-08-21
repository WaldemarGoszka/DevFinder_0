package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.RoleDAO;
import pl.devfinder.domain.Role;
import pl.devfinder.infrastructure.database.repository.jpa.RoleJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.RoleEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepository implements RoleDAO {
    RoleJpaRepository roleJpaRepository;
    RoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findByRole(String role) {
        return roleJpaRepository.findByRole(role)
                .map(roleEntityMapper::mapFromEntity);
    }
}
