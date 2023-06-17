package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.domain.Candidate;

@Mapper(componentModel = "spring")

public interface CandidateRowMapper {
    CandidateRowDTO map(final Candidate candidate);
}
