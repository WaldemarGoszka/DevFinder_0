package pl.devfinder.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "verificationTokenId")
@ToString(of = {"verificationTokenId", "token", "expirationTime"})
public class EmailVerificationToken {
     Long verificationTokenId;
     String token;
     OffsetDateTime expirationTime;
     User user;
}
