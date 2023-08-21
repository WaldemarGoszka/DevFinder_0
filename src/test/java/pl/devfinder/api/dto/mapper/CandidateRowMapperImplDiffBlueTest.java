package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Candidate;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {CandidateRowMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CandidateRowMapperImplDiffBlueTest {
    @Autowired
    private CandidateRowMapperImpl candidateRowMapperImpl;

    /**
     * Method under test: {@link CandidateRowMapperImpl#map(Candidate)}
     */
    @Test
    void testMap() {
        assertNull(candidateRowMapperImpl.map(null));
    }
}

