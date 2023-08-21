package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated("net.hexar.json2pojo")
public class Skill {

    private Long level;
    private String name;

}
