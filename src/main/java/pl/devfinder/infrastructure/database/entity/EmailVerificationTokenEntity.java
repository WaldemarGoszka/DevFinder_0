package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "verificationTokenId")
@ToString(of = {"verificationTokenId", "token"})
@Entity
@Table(name  = "email_verification_token")
public class EmailVerificationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_token_id")
    private Long verificationTokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "expiration_time")

    private OffsetDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
