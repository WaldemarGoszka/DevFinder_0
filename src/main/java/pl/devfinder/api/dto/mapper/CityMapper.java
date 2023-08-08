package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.CityDTO;
import pl.devfinder.domain.City;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDTO map(City city);

}
