package pl.devfinder.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

//    private String userName;
//    private String userUuid;
    private String email;
    @Size(min = 8, message = "Invalid password (too short)")
    private String password;
    private String repeatPassword;
    private Boolean active;
    private Role role;
}
