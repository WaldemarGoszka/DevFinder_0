package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;

@UtilityClass
public class UserEntityFixtures {

    public static UserEntity someEmptyUserEntity() {
        return UserEntity.builder()
                .userName("empty")
                .userUuid("empty")
                .email("empty")
                .password("empty")
                .isEnabled(true)
                .roleId(RoleEntity.builder()
                        .id(1L)
                        .role(Keys.Role.CANDIDATE.getName())
                        .build())
                .build();
    }

    public static UserEntity someUserEntityCandidate1() {
        return UserEntity.builder()
                .userName("candidate1UserName")
                .userUuid("candidate1-000000-000000")
                .email("candidate1Email1@test.com")
                .password("test")
                .isEnabled(true)
                .roleId(RoleEntity.builder()
                        .id(1L)
                        .role(Keys.Role.CANDIDATE.getName())
                        .build())
                .build();
    }

    public static User someUserCandidate1() {
        return User.builder()
                .userName("candidate1UserName")
                .userUuid("candidate1-000000-000000")
                .email("candidate1Email1@test.com")
                .password("test")
                .isEnabled(true)
                .role(Role.builder()
                        .id(1L)
                        .role(Keys.Role.CANDIDATE.getName())
                        .build())
                .build();
    }

    public static UserEntity someUserEntityCandidate2() {
        return UserEntity.builder()
                .userName("candidate2UserName")
                .userUuid("candidate2-000000-000000")
                .email("candidate2Email@test.com")
                .password("test")
                .isEnabled(true)
                .roleId(RoleEntity.builder()
                        .id(1L)
                        .role(Keys.Role.CANDIDATE.getName())
                        .build())
                .build();
    }

    public static UserEntity someUserEntityEmployer1() {
        return UserEntity.builder()
                .userName("employer1UserName")
                .userUuid("employer1-000000-000000")
                .email("employer1Email@test.com")
                .password("test")
                .isEnabled(true)
                .roleId(RoleEntity.builder()
                        .id(2L)
                        .role(Keys.Role.EMPLOYER.getName())
                        .build())
                .build();
    }

    public static UserEntity someUserEntityEmployer2() {
        return UserEntity.builder()
                .userName("employer2UserName")
                .userUuid("employer2-000000-000000")
                .email("employer2Email@test.com")
                .password("test")
                .isEnabled(true)
                .roleId(RoleEntity.builder()
                        .id(2L)
                        .role(Keys.Role.EMPLOYER.getName())
                        .build())
                .build();
    }
}
