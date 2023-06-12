package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.EmployerResultDTO;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring")
public interface EmployerResultMapper {

    EmployerResultDTO map(final Employer employer);
}
