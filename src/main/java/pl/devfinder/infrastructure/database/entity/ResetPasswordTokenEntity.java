package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "passwordResetTokenId")
@ToString(of = {"passwordResetTokenId", "token"})
@Entity
@Table(name  = "reset_password_token")
public class ResetPasswordTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_reset_token_id")
    private Long passwordResetTokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "expiration_time")
    private OffsetDateTime expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
