package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.domain.Offer;

@Mapper(componentModel = "spring")

public interface OfferRowMapper {
    OfferRowDTO map(final Offer offer);
}
