package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Employer;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {EmployerDetailsMapperImpl.class})
@ExtendWith(SpringExtension.class)
class EmployerDetailsMapperImplDiffBlueTest {
    @Autowired
    private EmployerDetailsMapperImpl employerDetailsMapperImpl;

    /**
     * Method under test: {@link EmployerDetailsMapperImpl#map(Employer)}
     */
    @Test
    void testMap() {
        assertNull(employerDetailsMapperImpl.map(null));
    }
}

