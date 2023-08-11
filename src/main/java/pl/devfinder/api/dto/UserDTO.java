package pl.devfinder.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Size(max = 32)
    @NotEmpty(message = "User Name should not be empty")
    private String userName;
    private String userUuid;
    @NotEmpty(message = "Email should not be empty")
    @Email
    @Size(max = 32)
    private String email;
    //    @Size(min = 8)
    @NotEmpty(message = "Password should not be empty")
    @Size(max = 32, message = "Invalid password")
    private String password;
    private Boolean isEnabled;
    @NotEmpty
    private String role;
}
