package pl.devfinder.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.entity.OfferEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferEntityMapper {

    Offer mapFromEntity(OfferEntity offerEntity);
}
