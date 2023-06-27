package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "userUuid")
@ToString(of = {"email"})
public class User {
    Long id;
    String userName;
    String userUuid;
    String email;
    String password;
    Boolean isEnabled;
    Role role;
}
