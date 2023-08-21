package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JustJoinItOffers {

    private List<JustJoinItOffer> justJoinItOffers;


}
