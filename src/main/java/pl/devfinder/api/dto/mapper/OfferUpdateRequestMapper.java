package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.devfinder.api.dto.OfferUpdateRequestDTO;
import pl.devfinder.domain.OfferUpdateRequest;

@Mapper(componentModel = "spring")

public interface OfferUpdateRequestMapper {
    OfferUpdateRequest map(final OfferUpdateRequestDTO offerUpdateRequestDTO);

}
