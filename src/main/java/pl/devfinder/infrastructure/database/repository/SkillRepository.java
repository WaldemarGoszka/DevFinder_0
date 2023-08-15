package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.SkillDAO;
import pl.devfinder.domain.Skill;
import pl.devfinder.infrastructure.database.repository.jpa.SkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.SkillEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillRepository implements SkillDAO {
    private final SkillJpaRepository skillJpaRepository;
    private final SkillEntityMapper skillEntityMapper;

    @Override
    public List<Skill> findAll() {
        return skillJpaRepository.findAllByOrderBySkillName().stream().map(skillEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Skill> findBySkillName(String skillName) {
        return skillJpaRepository.findBySkillName(skillName).map(skillEntityMapper::mapFromEntity);
    }
}
