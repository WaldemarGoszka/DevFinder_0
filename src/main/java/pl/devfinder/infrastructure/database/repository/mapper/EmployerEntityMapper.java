package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerEntityMapper {
    @Mapping(target = "cityId.employerCities", ignore = true)
    @Mapping(target = "cityId.offerCities", ignore = true)
    @Mapping(target = "cityId.candidateDesiredJobCities", ignore = true)
    @Mapping(target = "cityId.candidateResidenceCities", ignore = true)
    Employer mapFromEntity(EmployerEntity employerEntity);

    EmployerEntity mapToEntity(Employer employer);
}
