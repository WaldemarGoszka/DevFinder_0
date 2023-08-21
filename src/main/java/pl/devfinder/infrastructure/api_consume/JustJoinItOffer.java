package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JustJoinItOffer {

    private String address_text;
    private String city;
    private String company_logo_url;
    private String company_name;
    private String company_size;
    private String company_url;
    private String country_code;
    private Boolean display_offer;
    private List<EmploymentType> employment_types;
    private String experience_level;
    private String id;
    private String latitude;
    private String longitude;
    private String marker_icon;
    private List<Multilocation> multilocation;
    private Boolean open_to_hire_ukrainians;
    private String published_at;
    private Boolean remote;
    private Boolean remote_interview;
    private List<Skill> skills;
    private String street;
    private String title;
    private String way_of_apply;
    private String workplace_type;
}
