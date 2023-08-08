package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.OfferSkillDAO;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSkill;
import pl.devfinder.infrastructure.database.repository.jpa.OfferSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.OfferSkillEntityMapper;

import java.util.Set;

@Repository
@AllArgsConstructor
public class OfferSkillRepository implements OfferSkillDAO {
    private final OfferSkillJpaRepository offerSkillJpaRepository;
    private final OfferSkillEntityMapper offerSkillEntityMapper;
    private final OfferEntityMapper offerEntityMapper;

    @Override
    public void saveAll(Set<OfferSkill> offerSkills) {
        offerSkillJpaRepository.saveAll(offerSkills.stream().map(offerSkillEntityMapper::mapToEntity).toList());
    }

    @Override
    public void deleteAllByOffer(Offer offer) {
        offerSkillJpaRepository.deleteAllByOfferId(offerEntityMapper.mapToEntity(offer));

    }
}
