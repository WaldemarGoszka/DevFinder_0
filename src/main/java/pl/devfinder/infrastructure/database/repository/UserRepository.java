package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository implements UserDAO {
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userEntityMapper::mapFromEntity);

    }

    @Override
    public void save(UserDTO userDTO) {
        userJpaRepository.save()

    }
}
