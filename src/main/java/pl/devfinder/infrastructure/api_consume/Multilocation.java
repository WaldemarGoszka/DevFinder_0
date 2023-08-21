package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated("net.hexar.json2pojo")
public class Multilocation {
    private String city;
    private String slug;
    private String street;

}
