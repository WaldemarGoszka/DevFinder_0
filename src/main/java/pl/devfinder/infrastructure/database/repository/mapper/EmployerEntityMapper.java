package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerEntityMapper {

    Employer mapFromEntity(EmployerEntity employerEntity);

    EmployerEntity mapToEntity(Employer employer);
}
