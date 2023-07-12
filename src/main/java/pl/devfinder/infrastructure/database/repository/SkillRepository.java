package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.SkillDAO;
import pl.devfinder.domain.Skill;
import pl.devfinder.infrastructure.database.repository.jpa.SkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.SkillEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class SkillRepository implements SkillDAO {
    private final SkillJpaRepository skillJpaRepository;
    private final SkillEntityMapper skillEntityMapper;

    @Override
    public List<Skill> findAll() {
        return skillJpaRepository.findAll().stream().map(skillEntityMapper::mapFromEntity).toList();
    }
}
