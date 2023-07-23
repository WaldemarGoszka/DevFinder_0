package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring")
public interface EmployerDetailsMapper {
    EmployerDetailsDTO map(final Employer candidate);
}
