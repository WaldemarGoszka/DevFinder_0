package pl.devfinder.infrastructure.database.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.infrastructure.database.entity.SkillEntity;
import pl.devfinder.infrastructure.database.repository.jpa.SkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.SkillEntityMapper;

@ContextConfiguration(classes = {SkillRepository.class})
@ExtendWith(SpringExtension.class)
class SkillRepositoryDiffBlueTest {
    @MockBean
    private SkillEntityMapper skillEntityMapper;

    @MockBean
    private SkillJpaRepository skillJpaRepository;

    @Autowired
    private SkillRepository skillRepository;

    /**
     * Method under test: {@link SkillRepository#findAll()}
     */
    @Test
    void testFindAll() {
        when(skillJpaRepository.findAllByOrderBySkillName()).thenReturn(new ArrayList<>());
        assertTrue(skillRepository.findAll().isEmpty());
        verify(skillJpaRepository).findAllByOrderBySkillName();
    }

    /**
     * Method under test: {@link SkillRepository#findBySkillName(String)}
     */
    @Test
    void testFindBySkillName() {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Optional<SkillEntity> ofResult = Optional.of(skillEntity);
        when(skillJpaRepository.findBySkillName(Mockito.<String>any())).thenReturn(ofResult);
        when(skillEntityMapper.mapFromEntity(Mockito.<SkillEntity>any())).thenReturn(null);
        assertFalse(skillRepository.findBySkillName("Skill Name").isPresent());
        verify(skillJpaRepository).findBySkillName(Mockito.<String>any());
        verify(skillEntityMapper).mapFromEntity(Mockito.<SkillEntity>any());
    }
}

