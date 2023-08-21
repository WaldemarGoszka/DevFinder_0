package pl.devfinder.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.domain.Offer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferDetailsMapper {

    OfferDetailsDTO map(Offer offer);

}
