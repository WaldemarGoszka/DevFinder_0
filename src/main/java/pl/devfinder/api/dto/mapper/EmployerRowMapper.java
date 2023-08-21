package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring")
public interface EmployerRowMapper {
    EmployerRowDTO map(final Employer candidate);
}
