package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "cityName")
@ToString(of = {"cityId", "cityName"})
public class City {
    Long cityId;
    String cityName;


}
