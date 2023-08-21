package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.api.dto.OfferUpdateRequestDTO;
import pl.devfinder.domain.OfferUpdateRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface OfferUpdateRequestMapper {
    OfferUpdateRequest map(final OfferUpdateRequestDTO offerUpdateRequestDTO);

}
