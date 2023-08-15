package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.domain.Candidate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CandidateDetailsMapper {
    CandidateDetailsDTO map(final Candidate candidate);
    Candidate map(final CandidateDetailsDTO candidateDetailsDTO);

}
