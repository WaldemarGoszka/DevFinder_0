package pl.devfinder.infrastructure.api_consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated("net.hexar.json2pojo")
public class JustJoinItOfferDetails {

    private String address_text;
    private Object apply_body;
    private Object apply_url;
    private Object banner_url;
    private String body;
    private String city;
    private String company_logo_url;
    private String company_name;
    private String company_size;
    private String company_url;
    private String country_code;
    private String custom_consent;
    private String custom_consent_title;
    private List<EmploymentType> employment_types;
    private String experience_level;
    private String future_consent;
    private String future_consent_title;
    private String id;
    private String information_clause;
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
    private List<Object> tags;
    private String title;
    private Object video_key;
    private Object video_provider;
    private String workplace_type;

}
