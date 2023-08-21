package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.infrastructure.api_consume.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class JustJoinItApiService {

    public static final String MAIN_CURRENCY = "pln";
    public static final String B2B_CONTRACT = "b2b";
    public static final String PERMANENT_CONTRACT = "permanent";
    public static final Integer MINIMUM_NUMBER_OF_OCCURRENCES_OF_SKILL = 10;

    private static Map<String, Integer> getOffersWithSalaryRangesDistributionInParticularTechnology(String technologyName, List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .filter(offer -> technologyName.equals(offer.getMarker_icon()))
                .collect(Collectors.groupingByConcurrent(
                        offer -> {
                            if (offer.getEmployment_types() != null && !offer.getEmployment_types().isEmpty()) {
                                if (offer.getEmployment_types().get(0).getSalary() != null) {
                                    return "with";
                                } else {
                                    return "undisclosed";
                                }
                            } else {
                                return "undisclosed";
                            }
                        },
                        Collectors.summingInt(e -> 1)
                ));
    }

    private static Map<String, BigDecimal> getAvgSalaryByExperienceLevelPermanentContractInParticularTechnology(String technologyName, List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.stream()
                .filter(offer -> offer.getEmployment_types() != null && !offer.getEmployment_types().isEmpty())
                .filter(offer -> offer.getEmployment_types().get(0).getSalary() != null)
                .filter(offer -> MAIN_CURRENCY.equals(offer.getEmployment_types().get(0).getSalary().getCurrency()))
                .filter(offer -> technologyName.equals(offer.getMarker_icon()))
                .filter(offer -> PERMANENT_CONTRACT.equals(offer.getEmployment_types().get(0).getType()))
                .flatMap(offer -> offer.getEmployment_types().stream()
                        .filter(empType -> empType.getSalary() != null && empType.getSalary().getFrom() != null && empType.getSalary().getTo() != null)
                        .map(empType -> new AbstractMap.SimpleEntry<>(offer.getExperience_level(), empType)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(entry -> (entry.getValue().getSalary().getFrom().doubleValue() + entry.getValue().getSalary().getTo().doubleValue()) / 2.0)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> BigDecimal.valueOf(entry.getValue()).setScale(0, RoundingMode.HALF_UP)));
    }

    private static Map<String, BigDecimal> getAvgSalaryByExperienceLevelB2BContractInParticularTechnology(String technologyName, List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.stream()
                .filter(offer -> offer.getEmployment_types() != null && !offer.getEmployment_types().isEmpty())
                .filter(offer -> offer.getEmployment_types().get(0).getSalary() != null)
                .filter(offer -> MAIN_CURRENCY.equals(offer.getEmployment_types().get(0).getSalary().getCurrency()))
                .filter(offer -> technologyName.equals(offer.getMarker_icon()))
                .filter(offer -> B2B_CONTRACT.equals(offer.getEmployment_types().get(0).getType()))
                .flatMap(offer -> offer.getEmployment_types().stream()
                        .filter(empType -> empType.getSalary() != null && empType.getSalary().getFrom() != null && empType.getSalary().getTo() != null)
                        .map(empType -> new AbstractMap.SimpleEntry<>(offer.getExperience_level(), empType)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(entry -> (entry.getValue().getSalary().getFrom().doubleValue() + entry.getValue().getSalary().getTo().doubleValue()) / 2.0)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> BigDecimal.valueOf(entry.getValue()).setScale(0, RoundingMode.HALF_UP)));
    }

    private static LinkedHashMap<String, Integer> getSkillsInTechnologyDistributionInParticularTechnology(String technologyName, List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .filter(offer -> technologyName.equals(offer.getMarker_icon()))
                .flatMap(offer -> offer.getSkills().stream())
                .collect(Collectors.groupingByConcurrent(
                        Skill::getName,
                        Collectors.summingInt(e -> 1)
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private static Map<String, Integer> getExperienceDistributionInParticularTechnology(String technologyName, List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .filter(offer -> technologyName.equals(offer.getMarker_icon()))
                .collect(Collectors.groupingByConcurrent(
                        JustJoinItOffer::getExperience_level,
                        Collectors.summingInt(e -> 1)
                ));
    }

    private static Long getTotalOffersInParticularTechnology(String technologyName, List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .filter(offer -> technologyName.equals(offer.getMarker_icon()))
                .count();
    }

    private static Map<String, Integer> getOffersWithSalaryRangesDistributionInParticularTechnology(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        offer -> {
                            if (offer.getEmployment_types() != null && !offer.getEmployment_types().isEmpty()) {
                                if (offer.getEmployment_types().get(0).getSalary() != null) {
                                    return "with";
                                } else {
                                    return "undisclosed";
                                }
                            } else {
                                return "undisclosed";
                            }
                        },
                        Collectors.summingInt(e -> 1)
                ));
    }

    private static Set<String> getMainTechnologyList(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .map(JustJoinItOffer::getMarker_icon)
                .collect(Collectors.toSet());
    }

    private static Map<String, BigDecimal> getAvgSalaryByExperienceLevelPermanentContractInParticularTechnology(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.stream()
                .filter(offer -> offer.getEmployment_types() != null && !offer.getEmployment_types().isEmpty())
                .filter(offer -> offer.getEmployment_types().get(0).getSalary() != null)
                .filter(offer -> MAIN_CURRENCY.equals(offer.getEmployment_types().get(0).getSalary().getCurrency()))
                .filter(offer -> PERMANENT_CONTRACT.equals(offer.getEmployment_types().get(0).getType()))
                .flatMap(offer -> offer.getEmployment_types().stream()
                        .filter(empType -> empType.getSalary() != null && empType.getSalary().getFrom() != null && empType.getSalary().getTo() != null)
                        .map(empType -> new AbstractMap.SimpleEntry<>(offer.getExperience_level(), empType)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(entry -> (entry.getValue().getSalary().getFrom().doubleValue() + entry.getValue().getSalary().getTo().doubleValue()) / 2.0)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> BigDecimal.valueOf(entry.getValue()).setScale(0, RoundingMode.HALF_UP)));
    }

    private static Map<String, BigDecimal> getAvgSalaryByExperienceLevelB2BContract(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.stream()
                .filter(offer -> offer.getEmployment_types() != null && !offer.getEmployment_types().isEmpty())
                .filter(offer -> offer.getEmployment_types().get(0).getSalary() != null)
                .filter(offer -> MAIN_CURRENCY.equals(offer.getEmployment_types().get(0).getSalary().getCurrency()))
                .filter(offer -> B2B_CONTRACT.equals(offer.getEmployment_types().get(0).getType()))
                .flatMap(offer -> offer.getEmployment_types().stream()
                        .filter(empType -> empType.getSalary() != null && empType.getSalary().getFrom() != null && empType.getSalary().getTo() != null)
                        .map(empType -> new AbstractMap.SimpleEntry<>(offer.getExperience_level(), empType)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(entry -> (entry.getValue().getSalary().getFrom().doubleValue() + entry.getValue().getSalary().getTo().doubleValue()) / 2.0)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> BigDecimal.valueOf(entry.getValue()).setScale(0, RoundingMode.HALF_UP)));
    }

    private static LinkedHashMap<String, Integer> getSkillsDistribution(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .flatMap(offer -> offer.getSkills().stream())
                .collect(Collectors.groupingByConcurrent(
                        Skill::getName,
                        Collectors.summingInt(e -> 1)
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= MINIMUM_NUMBER_OF_OCCURRENCES_OF_SKILL)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private static Map<String, Integer> getSalaryDistribution(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .flatMap(offer -> offer.getEmployment_types().stream())
                .filter(employmentType -> employmentType.getSalary() != null)
                .map(employmentType -> employmentType.getSalary().getCurrency())
                .collect(Collectors.groupingByConcurrent(
                        currency -> currency,
                        Collectors.summingInt(e -> 1)
                ));
    }

    private static LinkedHashMap<String, Integer> getMainTechnologyDistribution(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        JustJoinItOffer::getMarker_icon,
                        Collectors.summingInt(e -> 1)
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private static Map<String, Integer> getEmploymentTypeDistribution(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .flatMap(offer -> offer.getEmployment_types().stream())
                .collect(Collectors.groupingByConcurrent(
                        EmploymentType::getType,
                        Collectors.summingInt(e -> 1)
                ));
    }

    private static Map<String, Integer> getExperienceLevelDistribution(List<JustJoinItOffer> justJoinItOffers) {
        return justJoinItOffers.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        JustJoinItOffer::getExperience_level,
                        Collectors.summingInt(e -> 1)
                ));
    }

    private static int getSize(Optional<List<JustJoinItOffer>> offers) {
        return offers.orElseThrow().size();
    }

    public Optional<JustJoinItOfferAnalysisResult> getAnalysisResult(Optional<List<JustJoinItOffer>> offers) {
        if (offers.isPresent()) {
            JustJoinItOfferAnalysisResult result = new JustJoinItOfferAnalysisResult();
            List<JustJoinItOffer> justJoinItOffers = offers.get();

            result.setTotalOffers(getSize(offers));

            result.setExperienceLevelDistribution(getExperienceLevelDistribution(justJoinItOffers));

            result.setEmploymentTypeDistribution(getEmploymentTypeDistribution(justJoinItOffers));

            result.setMainTechnologyDistribution(getMainTechnologyDistribution(justJoinItOffers));

            result.setSalaryCurrencyDistribution(getSalaryDistribution(justJoinItOffers));

            result.setSkillsDistribution(getSkillsDistribution(justJoinItOffers));

            result.setAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceDistribution(
                    getAvgSalaryByExperienceLevelB2BContract(justJoinItOffers));

            result.setAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceDistribution(
                    getAvgSalaryByExperienceLevelPermanentContractInParticularTechnology(justJoinItOffers));

            result.setOffersWithSalaryRangesExperienceDistribution(
                    getOffersWithSalaryRangesDistributionInParticularTechnology(justJoinItOffers));

            result.setAvailableTechnology(getMainTechnologyList(justJoinItOffers));

            return Optional.of(result);

        } else {
            return Optional.empty();
        }
    }

    public Optional<JustJoinItOfferAnalysisResultParticularTechnology> getAnalysisForParticularTechnology(
            Optional<List<JustJoinItOffer>> offers, String technologyName) {
        if (offers.isPresent()) {
            JustJoinItOfferAnalysisResultParticularTechnology result = new JustJoinItOfferAnalysisResultParticularTechnology();
            List<JustJoinItOffer> justJoinItOffers = offers.get();

            result.setTotalOffers(getSize(offers));
            result.setTotalOffersInParticularTechnology(
                    getTotalOffersInParticularTechnology(technologyName, justJoinItOffers));

            result.setExperienceInParticularDistribution(
                    getExperienceDistributionInParticularTechnology(technologyName, justJoinItOffers));

            result.setSkillsInInParticularTechnologyDistribution(
                    getSkillsInTechnologyDistributionInParticularTechnology(technologyName, justJoinItOffers));

            result.setAvgSalaryInPLNCurrencyB2BContractAccordingToExperienceInParticularTechnologyDistribution(
                    getAvgSalaryByExperienceLevelB2BContractInParticularTechnology(technologyName, justJoinItOffers));

            result.setAvgSalaryInPLNCurrencyPermanentContractAccordingToExperienceInParticularTechnologyDistribution(
                    getAvgSalaryByExperienceLevelPermanentContractInParticularTechnology(technologyName, justJoinItOffers));

            result.setAvailableTechnology(getMainTechnologyList(justJoinItOffers));

            result.setOffersWithSalaryRangesInParticularDistribution(
                    getOffersWithSalaryRangesDistributionInParticularTechnology(technologyName, justJoinItOffers));

            return Optional.of(result);

        } else {
            return Optional.empty();
        }
    }


}
