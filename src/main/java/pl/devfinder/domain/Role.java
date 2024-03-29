package pl.devfinder.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "role"})
public class Role {
    Long id;
    String role;
    Set<User> userId;
}
