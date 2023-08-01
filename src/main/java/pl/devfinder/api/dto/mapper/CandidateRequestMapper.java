package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.CandidateRequestDTO;
import pl.devfinder.domain.CandidateUpdateRequest;

@Mapper(componentModel = "spring")

public interface CandidateRequestMapper {
    CandidateUpdateRequest map(final CandidateRequestDTO candidateDetailsRequestDTO);

}
