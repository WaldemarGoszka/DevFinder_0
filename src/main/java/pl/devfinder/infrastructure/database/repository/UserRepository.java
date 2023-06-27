package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.List;
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
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId).map(userEntityMapper::mapFromEntity);
    }


    public User save(User user) {
        UserEntity userEntity = userEntityMapper.mapToEntity(user);
        return userEntityMapper.mapFromEntity(userJpaRepository.save(userEntity));
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return Optional.ofNullable(userEntityMapper.mapFromEntity(userJpaRepository.findByUserName(userName)));
    }

    @Override
    public void deleteById(Long userId) {
        userJpaRepository.deleteById(userId);
    }

    @Override
    public void update(String userName, String email, Long id) {
        userJpaRepository.update(userName, email, id);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(userEntityMapper::mapFromEntity).toList();
    }
}
