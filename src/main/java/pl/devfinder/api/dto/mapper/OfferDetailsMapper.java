package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.domain.Offer;

@Mapper(componentModel = "spring")
public interface OfferDetailsMapper {

    OfferDetailsDTO map(Offer offer);

}
