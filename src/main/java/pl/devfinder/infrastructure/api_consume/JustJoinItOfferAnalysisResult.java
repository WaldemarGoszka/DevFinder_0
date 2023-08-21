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
public class JustJoinItOfferAnalysisResult {

    private Integer totalOffers;
    private LinkedHashMap<String, Integer> skillsDistribution;
    private Map<String, Integer> experienceLevelDistribution;
    private Map<String, Integer> employmentTypeDistribution;
    private LinkedHashMap<String, Integer> mainTechnologyDistribution;
    private Map<String, Integer> salaryCurrencyDistribution;
    private Map<String, BigDecimal> avgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution;
    private Map<String, BigDecimal> avgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution;
    private Map<String, Integer> offersWithSalaryRangesExperienceDistribution;
    private Set<String> availableTechnology;

}
