package pl.devfinder.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 32, message = "Invalid password (too short)")
    private String password;
    private Boolean isEnabled;
    @NotEmpty
    private String role;
}
