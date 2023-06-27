package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.devfinder.infrastructure.database.entity.UserEntity;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    Optional<UserEntity> findByEmail(String email);
    @Modifying
    @Query(value = "UPDATE UserEntity u set u.userName =:userName, u.email =:email where u.id =:id")
    void update(String userName, String email, Long id);
}
