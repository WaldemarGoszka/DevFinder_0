package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Candidate;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {
//    @Mapping(target = "cityId.employerCities", ignore = true)
//    @Mapping(target = "cityId.offerCities", ignore = true)
//    @Mapping(target = "cityId.candidateDesiredJobCities", ignore = true)
//    @Mapping(target = "cityId.candidateResidenceCities", ignore = true)
    Candidate mapFromEntity(CandidateEntity candidateEntity);
}
