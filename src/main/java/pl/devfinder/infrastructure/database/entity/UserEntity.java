package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "userName", "email", "isEnabled", "roleId"})
@Entity
@With
@Table(name = "devfinder_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_uuid", unique = true, nullable = false)
    private String userUuid;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity roleId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private EmailVerificationTokenEntity emailVerificationToken;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private ResetPasswordTokenEntity resetPasswordTokenEntity;


}
