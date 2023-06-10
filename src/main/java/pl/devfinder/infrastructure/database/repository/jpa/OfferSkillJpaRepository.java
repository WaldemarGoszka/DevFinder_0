package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;

@Repository
public interface OfferSkillJpaRepository extends JpaRepository<OfferSkillEntity, Integer> {

}
