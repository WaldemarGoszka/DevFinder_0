package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JustJoinItOfferAnalysisResultParticularTechnology {

    private Integer totalOffers;
    private Long totalOffersInParticularTechnology;
    private Map<String, Integer> experienceInParticularDistribution;
    private LinkedHashMap<String, Integer> skillsInInParticularTechnologyDistribution;
    private Map<String, Integer> offersWithSalaryRangesInParticularDistribution;
    private Map<String, BigDecimal> avgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution;
    private Map<String, BigDecimal> avgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution;
    private Set<String> availableTechnology;


}
