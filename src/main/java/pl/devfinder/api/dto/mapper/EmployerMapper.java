package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.mapper.EmployerResultDTO;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

     EmployerResultDTO map(final Employer salesman);


}
