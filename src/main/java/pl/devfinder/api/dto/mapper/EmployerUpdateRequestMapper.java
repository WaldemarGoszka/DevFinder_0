package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.EmployerUpdateRequestDTO;
import pl.devfinder.domain.EmployerUpdateRequest;

@Mapper(componentModel = "spring")

public interface EmployerUpdateRequestMapper {
    EmployerUpdateRequest map(final EmployerUpdateRequestDTO employerUpdateRequestDTO);

}
