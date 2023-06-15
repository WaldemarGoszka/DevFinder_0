package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.business.OfferService;
import pl.devfinder.domain.Employer;

@Mapper(componentModel = "spring")
public abstract class EmployerRowMapper {
    @Autowired
    OfferService offerService;

    @Mapping(target = "numberOfAvailableOffers", ignore = true)
    //EmployerRowDTO map(final Employer employer);
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
        employerRowDTO.numberOfAvailableOffers(offerService.getNumberOfAvailableOffers(employer.getEmployerId()));
        return employerRowDTO.build();
    }

//    @AfterMapping
//    protected void getNumberOfAvailableOffers(@MappingTarget EmployerRowDTO employerRowDTO, Employer employer, @Context OfferService offerService) {
//        //employerRowDTO.setNumberOfAvailableOffers(offerService.getNumberOfAvailableOffers(employer));
//        System.out.println("****************");
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
