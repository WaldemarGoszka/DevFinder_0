package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Salary {

    private BigDecimal from;
    private BigDecimal to;
    private String currency;

}
