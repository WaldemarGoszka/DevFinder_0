package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.SkillEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillJpaRepository extends JpaRepository<SkillEntity, Long> {

    Optional<SkillEntity> findBySkillName(String skillName);

    List<SkillEntity> findAllByOrderBySkillName();
}
