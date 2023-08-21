package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Skill;
import pl.devfinder.infrastructure.database.entity.SkillEntity;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SkillEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class SkillEntityMapperImplDiffBlueTest {
    @Autowired
    private SkillEntityMapperImpl skillEntityMapperImpl;

    /**
     * Method under test: {@link SkillEntityMapperImpl#mapFromEntity(SkillEntity)}
     */
    @Test
    void testMapFromEntity() {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualMapFromEntityResult = skillEntityMapperImpl.mapFromEntity(skillEntity);
        assertEquals(1L, actualMapFromEntityResult.getSkillId().longValue());
        assertEquals("Skill Name", actualMapFromEntityResult.getSkillName());
    }

    /**
     * Method under test: {@link SkillEntityMapperImpl#mapFromEntity(SkillEntity)}
     */
    @Test
    void testMapFromEntity2() {
        SkillEntity skillEntity = mock(SkillEntity.class);
        when(skillEntity.getSkillId()).thenReturn(1L);
        when(skillEntity.getSkillName()).thenReturn("Skill Name");
        doNothing().when(skillEntity).setCandidateSkills(Mockito.any());
        doNothing().when(skillEntity).setOfferSkills(Mockito.any());
        doNothing().when(skillEntity).setSkillId(Mockito.<Long>any());
        doNothing().when(skillEntity).setSkillName(Mockito.any());
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualMapFromEntityResult = skillEntityMapperImpl.mapFromEntity(skillEntity);
        assertEquals(1L, actualMapFromEntityResult.getSkillId().longValue());
        assertEquals("Skill Name", actualMapFromEntityResult.getSkillName());
        verify(skillEntity).getSkillId();
        verify(skillEntity).getSkillName();
        verify(skillEntity).setCandidateSkills(Mockito.any());
        verify(skillEntity).setOfferSkills(Mockito.any());
        verify(skillEntity).setSkillId(Mockito.<Long>any());
        verify(skillEntity).setSkillName(Mockito.any());
    }
}

