package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Employer;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {EmployerRowMapperImpl.class})
@ExtendWith(SpringExtension.class)
class EmployerRowMapperImplDiffBlueTest {
    @Autowired
    private EmployerRowMapperImpl employerRowMapperImpl;

    /**
     * Method under test: {@link EmployerRowMapperImpl#map(Employer)}
     */
    @Test
    void testMap() {
        assertNull(employerRowMapperImpl.map(null));
    }
}

