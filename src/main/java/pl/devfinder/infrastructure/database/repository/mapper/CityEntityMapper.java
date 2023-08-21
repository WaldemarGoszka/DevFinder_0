package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.City;
import pl.devfinder.infrastructure.database.entity.CityEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityEntityMapper {

    City mapFromEntity(CityEntity cityEntity);

    CityEntity mapToEntity(City city);
}
