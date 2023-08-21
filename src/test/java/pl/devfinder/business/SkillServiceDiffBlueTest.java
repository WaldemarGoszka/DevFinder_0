package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.SkillDAO;
import pl.devfinder.domain.Skill;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SkillService.class})
@ExtendWith(SpringExtension.class)
class SkillServiceDiffBlueTest {
    @MockBean
    private SkillDAO skillDAO;

    @Autowired
    private SkillService skillService;

    /**
     * Method under test: {@link SkillService#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<Skill> skillList = new ArrayList<>();
        when(skillDAO.findAll()).thenReturn(skillList);
        List<Skill> actualFindAllResult = skillService.findAll();
        assertSame(skillList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(skillDAO).findAll();
    }

    /**
     * Method under test: {@link SkillService#findBySkillName(String)}
     */
    @Test
    void testFindBySkillName() {
        Optional<Skill> emptyResult = Optional.empty();
        when(skillDAO.findBySkillName(Mockito.any())).thenReturn(emptyResult);
        Optional<Skill> actualFindBySkillNameResult = skillService.findBySkillName("Skill Name");
        assertSame(emptyResult, actualFindBySkillNameResult);
        assertFalse(actualFindBySkillNameResult.isPresent());
        verify(skillDAO).findBySkillName(Mockito.any());
    }

    /**
     * Method under test: {@link SkillService#findBySkillName(String)}
     */
    @Test
    void testFindBySkillName2() {
        when(skillDAO.findBySkillName(Mockito.any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> skillService.findBySkillName("Skill Name"));
        verify(skillDAO).findBySkillName(Mockito.any());
    }

    /**
     * Method under test: {@link SkillService#buildSkill(String)}
     */
    @Test
    void testBuildSkill() {
        when(skillDAO.findBySkillName(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillService.buildSkill("Skill Name"));
        verify(skillDAO).findBySkillName(Mockito.any());
    }

    /**
     * Method under test: {@link SkillService#buildSkill(String)}
     */
    @Test
    void testBuildSkill2() {
        when(skillDAO.findBySkillName(Mockito.any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> skillService.buildSkill("Skill Name"));
        verify(skillDAO).findBySkillName(Mockito.any());
    }
}

