package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerDetailsMapper {
    EmployerDetailsDTO map(final Employer candidate);
}
