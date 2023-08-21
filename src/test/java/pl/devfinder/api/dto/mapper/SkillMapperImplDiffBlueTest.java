package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Skill;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {SkillMapperImpl.class})
@ExtendWith(SpringExtension.class)
class SkillMapperImplDiffBlueTest {
    @Autowired
    private SkillMapperImpl skillMapperImpl;

    /**
     * Method under test: {@link SkillMapperImpl#map(Skill)}
     */
    @Test
    void testMap() {
        assertNull(skillMapperImpl.map(null));
    }
}

