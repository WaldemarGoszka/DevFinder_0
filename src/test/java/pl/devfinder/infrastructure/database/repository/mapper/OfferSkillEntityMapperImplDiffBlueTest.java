package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {OfferSkillEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class OfferSkillEntityMapperImplDiffBlueTest {
    @Autowired
    private OfferSkillEntityMapperImpl offerSkillEntityMapperImpl;

    /**
     * Method under test: {@link OfferSkillEntityMapperImpl#mapToEntity(OfferSkill)}
     */
    @Test
    void testMapToEntity() {
        assertNull(offerSkillEntityMapperImpl.mapToEntity(null));
    }

    /**
     * Method under test: {@link OfferSkillEntityMapperImpl#cityToCityEntity(City)}
     */
    @Test
    void testCityToCityEntity() {
        assertNull(offerSkillEntityMapperImpl.cityToCityEntity(null));
    }

    /**
     * Method under test: {@link OfferSkillEntityMapperImpl#employerToEmployerEntity(Employer)}
     */
    @Test
    void testEmployerToEmployerEntity() {
        assertNull(offerSkillEntityMapperImpl.employerToEmployerEntity(null));
    }

    /**
     * Method under test: {@link OfferSkillEntityMapperImpl#offerSkillSetToOfferSkillEntitySet(Set)}
     */
    @Test
    void testOfferSkillSetToOfferSkillEntitySet() {
        assertTrue(offerSkillEntityMapperImpl.offerSkillSetToOfferSkillEntitySet(new HashSet<>()).isEmpty());
    }

    /**
     * Method under test: {@link OfferSkillEntityMapperImpl#offerToOfferEntity(Offer)}
     */
    @Test
    void testOfferToOfferEntity() {
        assertNull(offerSkillEntityMapperImpl.offerToOfferEntity(null));
    }

    /**
     * Method under test: {@link OfferSkillEntityMapperImpl#skillToSkillEntity(Skill)}
     */
    @Test
    void testSkillToSkillEntity() {
        assertNull(offerSkillEntityMapperImpl.skillToSkillEntity(null));
    }
}

