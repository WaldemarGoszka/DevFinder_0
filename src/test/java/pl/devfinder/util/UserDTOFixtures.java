package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.management.Keys;

@UtilityClass
public class UserDTOFixtures {


    public static UserDTO someUserCandidateDTO1() {
        return UserDTO.builder()
                .userName("candidate1UserName")
                .userUuid("candidate1-000000-000000")
                .email("candidate1Email1@test.com")
                .password("test")
                .isEnabled(true)
                .role(Keys.Role.CANDIDATE.getName())
                .build();
    }

}
