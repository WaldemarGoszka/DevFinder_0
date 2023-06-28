package pl.devfinder.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "passwordResetTokenId")
@ToString(of = {"passwordResetTokenId", "token", "expirationTime"})
public class ResetPasswordToken {
    Long passwordResetTokenId;
    String token;
    OffsetDateTime expirationTime;
    User user;
}
