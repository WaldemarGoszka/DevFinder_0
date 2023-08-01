package pl.devfinder.business.dao;

import pl.devfinder.domain.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillDAO {

    List<Skill> findAll();

    Optional<Skill> findBySkillName(String skillName);
}
