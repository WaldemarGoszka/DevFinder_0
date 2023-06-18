package pl.devfinder.domain;

import lombok.*;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
@With
@Value
@Builder
@EqualsAndHashCode(of = "userUuid")
@ToString(of = {"email"})
public class User {

     String userName;
     String userUuid;
     String email;
     String password;
     Boolean active;
     Role roleId;
}
