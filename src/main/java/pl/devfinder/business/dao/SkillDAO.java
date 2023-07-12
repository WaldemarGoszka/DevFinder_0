package pl.devfinder.business.dao;

import pl.devfinder.domain.Skill;

import java.util.List;

public interface SkillDAO {

    List<Skill> findAll();
}
