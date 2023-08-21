package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.CandidateSkillDAO;
import pl.devfinder.domain.CandidateSkill;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {CandidateSkillService.class})
@ExtendWith(SpringExtension.class)
class CandidateSkillServiceDiffBlueTest {
    @MockBean
    private CandidateSkillDAO candidateSkillDAO;

    @Autowired
    private CandidateSkillService candidateSkillService;

    /**
     * Method under test: {@link CandidateSkillService#saveAll(Set)}
     */
    @Test
    void testSaveAll() {
        doNothing().when(candidateSkillDAO).saveAll(Mockito.any());
        candidateSkillService.saveAll(new HashSet<>());
        verify(candidateSkillDAO).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillService#saveAll(Set)}
     */
    @Test
    void testSaveAll2() {
        doNothing().when(candidateSkillDAO).saveAll(Mockito.any());

        HashSet<CandidateSkill> candidateSkills = new HashSet<>();
        candidateSkills.add(new CandidateSkill(1L, null, null));
        candidateSkillService.saveAll(candidateSkills);
        verify(candidateSkillDAO).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillService#saveAll(Set)}
     */
    @Test
    void testSaveAll3() {
        doNothing().when(candidateSkillDAO).saveAll(Mockito.any());

        HashSet<CandidateSkill> candidateSkills = new HashSet<>();
        candidateSkills.add(null);
        candidateSkills.add(new CandidateSkill(1L, null, null));
        candidateSkillService.saveAll(candidateSkills);
        verify(candidateSkillDAO).saveAll(Mockito.any());
    }


}

