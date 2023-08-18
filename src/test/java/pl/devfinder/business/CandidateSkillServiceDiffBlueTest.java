package pl.devfinder.business;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.CandidateSkillDAO;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;

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
        doNothing().when(candidateSkillDAO).saveAll(Mockito.<Set<CandidateSkill>>any());
        candidateSkillService.saveAll(new HashSet<>());
        verify(candidateSkillDAO).saveAll(Mockito.<Set<CandidateSkill>>any());
    }

    /**
     * Method under test: {@link CandidateSkillService#saveAll(Set)}
     */
    @Test
    void testSaveAll2() {
        doNothing().when(candidateSkillDAO).saveAll(Mockito.<Set<CandidateSkill>>any());

        HashSet<CandidateSkill> candidateSkills = new HashSet<>();
        candidateSkills.add(new CandidateSkill(1L, null, null));
        candidateSkillService.saveAll(candidateSkills);
        verify(candidateSkillDAO).saveAll(Mockito.<Set<CandidateSkill>>any());
    }

    /**
     * Method under test: {@link CandidateSkillService#saveAll(Set)}
     */
    @Test
    void testSaveAll3() {
        doNothing().when(candidateSkillDAO).saveAll(Mockito.<Set<CandidateSkill>>any());

        HashSet<CandidateSkill> candidateSkills = new HashSet<>();
        candidateSkills.add(null);
        candidateSkills.add(new CandidateSkill(1L, null, null));
        candidateSkillService.saveAll(candidateSkills);
        verify(candidateSkillDAO).saveAll(Mockito.<Set<CandidateSkill>>any());
    }



}

