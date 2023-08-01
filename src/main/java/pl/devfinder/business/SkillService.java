package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.api.dto.SkillDTO;
import pl.devfinder.business.dao.SkillDAO;
import pl.devfinder.domain.Skill;

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
}
