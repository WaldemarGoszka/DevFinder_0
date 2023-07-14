package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring")
public abstract class EmployerRowMapper {
    @Autowired
    private OfferService offerService;

    public EmployerRowDTO map(Employer employer) {
        if (employer == null) {
            return null;
        }
        EmployerRowDTO.EmployerRowDTOBuilder employerRowDTO = EmployerRowDTO.builder();
        employerRowDTO.companyName(employer.getCompanyName());
        employerRowDTO.logoFile(employer.getLogoFile());
        employerRowDTO.website(employer.getWebsite());
        employerRowDTO.numberOfEmployees(employer.getNumberOfEmployees());
        employerRowDTO.cityId(employer.getCityId());
        employerRowDTO.numberOfAvailableOffers(offerService.getNumberOfOffersByEmployerAndByState(employer.getEmployerId(), Keys.OfferState.ACTIVE));
        return employerRowDTO.build();
    }

//    @AfterMapping
//    protected void getNumberOfAvailableOffers(@MappingTarget EmployerRowDTO employerRowDTO, Employer employer, @Context OfferService offerService) {
//        //employerRowDTO.setNumberOfAvailableOffers(offerService.getNumberOfAvailableOffers(employer));
//        //employerRowDTO.numberOfAvailableOffers(2);
//        //return offerService.getNumberOfAvailableOffers(employer);
//        //return 5;
//
//    }

//    @Mapper(componentModel = "spring", uses = {OfferService.class})
//    public interface EmployerRowMapper {
//        OfferService offerService =  Mappers.getMapper(OfferService.class);
//        @Mapping(target = "numberOfAvailableOffers", expression = "java(getNumberOfAvailableOffers(employer))")
//        EmployerRowDTO map(final Employer employer);
//        default Integer getNumberOfAvailableOffers(Employer employer) {
//            return offerService.getNumberOfAvailableOffers(employer);
//        }
}
