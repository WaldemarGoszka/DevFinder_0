package pl.devfinder.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.devfinder.infrastructure.configuration.PersistenceContainerTestConfiguration;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.util.UserEntityFixtures;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserJpaTest {
    private UserJpaRepository userJpaRepository;

    @Test
    void thatUserCanBeSavedCorrectly() {
        // given
        var cars = List.of(UserEntityFixtures.someUserEntityCandidate1(),
                UserEntityFixtures.someUserEntityCandidate2(),
                UserEntityFixtures.someUserEntityEmployer1(),
                UserEntityFixtures.someUserEntityEmployer2());
        userJpaRepository.saveAllAndFlush(cars);

        // when
        List<UserEntity> userEntities = userJpaRepository.findAll();

        // then
        assertThat(userEntities).hasSize(58);
    }

    @Test
    public void testFindByUserName() {
        // given
        String userName = "kowalski";
        UserEntity userEntity = UserEntityFixtures.someEmptyUserEntity();
        userEntity.setUserName(userName);
        userJpaRepository.save(userEntity);

        // when
        UserEntity result = userJpaRepository.findByUserName(userName);

        // then
        assertNotNull(result);
        assertEquals(userName, result.getUserName());
    }

    @Test
    public void testFindByEmail() {
        // given
        String email = "test@example.com";
        UserEntity userEntity = UserEntityFixtures.someEmptyUserEntity();
        userEntity.setEmail(email);
        userJpaRepository.save(userEntity);

        // when
        Optional<UserEntity> result = userJpaRepository.findByEmail(email);

        // then
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }
}
