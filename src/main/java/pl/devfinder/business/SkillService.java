package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.SkillDAO;
import pl.devfinder.domain.Skill;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SkillService {
    private final SkillDAO skillDAO;

    public List<Skill> findAll() {
        return skillDAO.findAll();
    }

    public Optional<Skill> findBySkillName(String skillName) {
        return skillDAO.findBySkillName(skillName);
    }

    public Skill buildSkill(String skillName) {
        Skill skill = this.findBySkillName(skillName)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        return Skill.builder()
                .skillId(skill.getSkillId())
                .skillName(skillName)
                .build();
    }
}
