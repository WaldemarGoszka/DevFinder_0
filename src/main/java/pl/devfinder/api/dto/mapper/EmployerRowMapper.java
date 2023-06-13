package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.business.OfferService;
import pl.devfinder.domain.Employer;

//@Mapper(uses = {OfferService.class}, componentModel = "spring")
@Mapper(componentModel = "spring")
public interface EmployerRowMapper {


    @Mapping(target = "numberOfAvailableOffers", expression = "java(getNumberOfAvailableOffers(employer))")
    EmployerRowDTO map(final Employer employer);
    default Integer getNumberOfAvailableOffers(Employer employer) {
//        OfferService offerService = Mappers.getMapper(OfferService.class);
//        return offerService.getNumberOfAvailableOffers(employer);
        return 5;
    }

//    public interface EmployerMapper {
//        EmployerMapper INSTANCE = Mappers.getMapper(EmployerMapper.class);
//
//        @Mapping(target = "NumberOfAvailableOffers", source = "this")
//        EmployerRowDTO toEmployerRowDTO(Employer employer);
//
//        default Integer getNumberOfAvailableOffers(Employer employer) {
//            return offerService.getNumberOfAvailableOffers(employer);
//        }
//    }
}
