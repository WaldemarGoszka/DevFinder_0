package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.business.OfferService;
import pl.devfinder.domain.Offer;

@Mapper(componentModel = "spring")

public abstract class OfferRowMapper {
@Autowired
    OfferService offerService;

    public OfferRowDTO map(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferRowDTO.OfferRowDTOBuilder offerRowDTO = OfferRowDTO.builder();

        offerRowDTO.title( offer.getTitle() );
        offerRowDTO.remoteWorkFormated( offerService.formatRemoteWork(offer.getRemoteWork()) );
        offerRowDTO.experienceLevel( offer.getExperienceLevel() );
        offerRowDTO.salaryRange(offerService.formatSalaryRange(offer.getSalaryMin(), offer.getSalaryMax()) );
        offerRowDTO.employerId( offer.getEmployerId() );
        offerRowDTO.cityId( offer.getCityId() );
        offerRowDTO.daysSinceCreated(offerService.getDaysSinceDate(offer.getCreatedAt()));

        return offerRowDTO.build();
    }
}
