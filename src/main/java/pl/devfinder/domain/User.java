package pl.devfinder.domain;

import pl.devfinder.infrastructure.database.entity.RoleEntity;

public class User {

    private String userName;
    private String userUuid;
    private String email;
    private String password;
    private Boolean active;
    private RoleEntity roleId;
}
