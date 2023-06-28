package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devfinder_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private EmailVerificationTokenEntity emailVerificationToken;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private ResetPasswordTokenEntity resetPasswordTokenEntity;


}
