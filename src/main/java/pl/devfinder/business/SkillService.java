package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.api.dto.SkillDTO;
import pl.devfinder.business.dao.SkillDAO;
import pl.devfinder.domain.Skill;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillService {
    private final SkillDAO skillDAO;

    public List<Skill> findAll() {
        return skillDAO.findAll();
    }
}
