package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.configuration.PersistenceContainerTestConfiguration;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.UserEntityMapper;
import pl.devfinder.util.UserEntityFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
class UserRepositorySpringBootTest {

    private UserJpaRepository userJpaRepository;
    private UserEntityMapper userEntityMapper;

//    @InjectMocks
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // given
        String email = "test@example.com";
        UserEntity userEntityToSave = UserEntityFixtures.someUserEntityCandidate1().withEmail(email);

        // when
        UserEntity userEntity = userJpaRepository.saveAndFlush(userEntityToSave);
        User user = UserFixtures.someUserCandidate1().withEmail(email);
        Optional<User> result = userRepository.findByEmail(email);

        // then
        assertEquals(userJpaRepository.findByEmail(email),Optional.of(userEntity));
        assertTrue(result.isPresent());
        assertEquals(result.get().getEmail(),user.getEmail());
    }

    @Test
    void testFindById() {
        // given
        Long userId = 1L;
        UserEntity userEntityToSave = UserEntityFixtures.someUserEntityCandidate1().withId(userId);

        // when
        UserEntity userEntity = userJpaRepository.saveAndFlush(userEntityToSave);
        User user = UserFixtures.someUserCandidate1().withId(userId);
        Optional<User> result = userRepository.findById(userId);

        assertEquals(userJpaRepository.findById(userId),Optional.of(userEntity));
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
    }





}