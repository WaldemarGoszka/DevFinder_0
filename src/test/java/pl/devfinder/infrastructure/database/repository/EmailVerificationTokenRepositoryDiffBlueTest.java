package pl.devfinder.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;
import pl.devfinder.infrastructure.database.entity.ResetPasswordTokenEntity;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.infrastructure.database.repository.jpa.EmailVerificationTokenJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmailVerificationTokenMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmailVerificationTokenRepository.class})
@ExtendWith(SpringExtension.class)
class EmailVerificationTokenRepositoryDiffBlueTest {
    @MockBean
    private EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository;

    @MockBean
    private EmailVerificationTokenMapper emailVerificationTokenMapper;

    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    /**
     * Method under test: {@link EmailVerificationTokenRepository#deleteByUserId(Long)}
     */
    @Test
    void testDeleteByUserId() {
        doNothing().when(emailVerificationTokenJpaRepository).deleteByUserId(Mockito.<Long>any());
        emailVerificationTokenRepository.deleteByUserId(1L);
        verify(emailVerificationTokenJpaRepository).deleteByUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmailVerificationTokenRepository#findByToken(String)}
     */
    @Test
    void testFindByToken() {
        UserEntity user = new UserEntity();
        user.setEmail("jane.doe@example.org");
        user.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user.setId(1L);
        user.setIsEnabled(true);
        user.setPassword("iloveyou");
        user.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user.setRoleId(new RoleEntity());
        user.setUserName("janedoe");
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken = new EmailVerificationTokenEntity();
        emailVerificationToken
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken.setToken("ABC123");
        emailVerificationToken.setUser(user);
        emailVerificationToken.setVerificationTokenId(1L);

        UserEntity user2 = new UserEntity();
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user2.setId(1L);
        user2.setIsEnabled(true);
        user2.setPassword("iloveyou");
        user2.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user2.setRoleId(new RoleEntity());
        user2.setUserName("janedoe");
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity.setToken("ABC123");
        resetPasswordTokenEntity.setUser(user2);

        RoleEntity roleId = new RoleEntity();
        roleId.setId(1L);
        roleId.setRole("Role");
        roleId.setUserId(new HashSet<>());

        UserEntity user3 = new UserEntity();
        user3.setEmail("jane.doe@example.org");
        user3.setEmailVerificationToken(emailVerificationToken);
        user3.setId(1L);
        user3.setIsEnabled(true);
        user3.setPassword("iloveyou");
        user3.setResetPasswordTokenEntity(resetPasswordTokenEntity);
        user3.setRoleId(roleId);
        user3.setUserName("janedoe");
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationTokenEntity = new EmailVerificationTokenEntity();
        emailVerificationTokenEntity
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationTokenEntity.setToken("ABC123");
        emailVerificationTokenEntity.setUser(user3);
        emailVerificationTokenEntity.setVerificationTokenId(1L);
        Optional<EmailVerificationTokenEntity> ofResult = Optional.of(emailVerificationTokenEntity);
        when(emailVerificationTokenJpaRepository.findByToken(Mockito.any())).thenReturn(ofResult);
        when(emailVerificationTokenMapper.mapFromEntity(Mockito.any())).thenReturn(null);
        assertFalse(emailVerificationTokenRepository.findByToken("ABC123").isPresent());
        verify(emailVerificationTokenJpaRepository).findByToken(Mockito.any());
        verify(emailVerificationTokenMapper).mapFromEntity(Mockito.any());
    }

    /**
     * Method under test: {@link EmailVerificationTokenRepository#save(EmailVerificationToken)}
     */
    @Test
    void testSave() {
        EmailVerificationTokenEntity emailVerificationToken = new EmailVerificationTokenEntity();
        emailVerificationToken
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken.setToken("ABC123");
        emailVerificationToken.setUser(new UserEntity());
        emailVerificationToken.setVerificationTokenId(1L);

        ResetPasswordTokenEntity resetPasswordTokenEntity = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity.setToken("ABC123");
        resetPasswordTokenEntity.setUser(new UserEntity());

        RoleEntity roleId = new RoleEntity();
        roleId.setId(1L);
        roleId.setRole("Role");
        roleId.setUserId(new HashSet<>());

        UserEntity user = new UserEntity();
        user.setEmail("jane.doe@example.org");
        user.setEmailVerificationToken(emailVerificationToken);
        user.setId(1L);
        user.setIsEnabled(true);
        user.setPassword("iloveyou");
        user.setResetPasswordTokenEntity(resetPasswordTokenEntity);
        user.setRoleId(roleId);
        user.setUserName("janedoe");
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken2 = new EmailVerificationTokenEntity();
        emailVerificationToken2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken2.setToken("ABC123");
        emailVerificationToken2.setUser(user);
        emailVerificationToken2.setVerificationTokenId(1L);

        EmailVerificationTokenEntity emailVerificationToken3 = new EmailVerificationTokenEntity();
        emailVerificationToken3
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken3.setToken("ABC123");
        emailVerificationToken3.setUser(new UserEntity());
        emailVerificationToken3.setVerificationTokenId(1L);

        ResetPasswordTokenEntity resetPasswordTokenEntity2 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity2.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity2.setToken("ABC123");
        resetPasswordTokenEntity2.setUser(new UserEntity());

        RoleEntity roleId2 = new RoleEntity();
        roleId2.setId(1L);
        roleId2.setRole("Role");
        roleId2.setUserId(new HashSet<>());

        UserEntity user2 = new UserEntity();
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerificationToken(emailVerificationToken3);
        user2.setId(1L);
        user2.setIsEnabled(true);
        user2.setPassword("iloveyou");
        user2.setResetPasswordTokenEntity(resetPasswordTokenEntity2);
        user2.setRoleId(roleId2);
        user2.setUserName("janedoe");
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity3 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity3
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity3.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity3.setToken("ABC123");
        resetPasswordTokenEntity3.setUser(user2);

        RoleEntity roleId3 = new RoleEntity();
        roleId3.setId(1L);
        roleId3.setRole("Role");
        roleId3.setUserId(new HashSet<>());

        UserEntity user3 = new UserEntity();
        user3.setEmail("jane.doe@example.org");
        user3.setEmailVerificationToken(emailVerificationToken2);
        user3.setId(1L);
        user3.setIsEnabled(true);
        user3.setPassword("iloveyou");
        user3.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        user3.setRoleId(roleId3);
        user3.setUserName("janedoe");
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationTokenEntity = new EmailVerificationTokenEntity();
        emailVerificationTokenEntity
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationTokenEntity.setToken("ABC123");
        emailVerificationTokenEntity.setUser(user3);
        emailVerificationTokenEntity.setVerificationTokenId(1L);
        when(emailVerificationTokenJpaRepository.save(Mockito.any()))
                .thenReturn(emailVerificationTokenEntity);

        EmailVerificationTokenEntity emailVerificationToken4 = new EmailVerificationTokenEntity();
        emailVerificationToken4
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken4.setToken("ABC123");
        emailVerificationToken4.setUser(new UserEntity());
        emailVerificationToken4.setVerificationTokenId(1L);

        ResetPasswordTokenEntity resetPasswordTokenEntity4 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity4
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity4.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity4.setToken("ABC123");
        resetPasswordTokenEntity4.setUser(new UserEntity());

        RoleEntity roleId4 = new RoleEntity();
        roleId4.setId(1L);
        roleId4.setRole("Role");
        roleId4.setUserId(new HashSet<>());

        UserEntity user4 = new UserEntity();
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerificationToken(emailVerificationToken4);
        user4.setId(1L);
        user4.setIsEnabled(true);
        user4.setPassword("iloveyou");
        user4.setResetPasswordTokenEntity(resetPasswordTokenEntity4);
        user4.setRoleId(roleId4);
        user4.setUserName("janedoe");
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken5 = new EmailVerificationTokenEntity();
        emailVerificationToken5
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken5.setToken("ABC123");
        emailVerificationToken5.setUser(user4);
        emailVerificationToken5.setVerificationTokenId(1L);

        EmailVerificationTokenEntity emailVerificationToken6 = new EmailVerificationTokenEntity();
        emailVerificationToken6
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken6.setToken("ABC123");
        emailVerificationToken6.setUser(new UserEntity());
        emailVerificationToken6.setVerificationTokenId(1L);

        ResetPasswordTokenEntity resetPasswordTokenEntity5 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity5
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity5.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity5.setToken("ABC123");
        resetPasswordTokenEntity5.setUser(new UserEntity());

        RoleEntity roleId5 = new RoleEntity();
        roleId5.setId(1L);
        roleId5.setRole("Role");
        roleId5.setUserId(new HashSet<>());

        UserEntity user5 = new UserEntity();
        user5.setEmail("jane.doe@example.org");
        user5.setEmailVerificationToken(emailVerificationToken6);
        user5.setId(1L);
        user5.setIsEnabled(true);
        user5.setPassword("iloveyou");
        user5.setResetPasswordTokenEntity(resetPasswordTokenEntity5);
        user5.setRoleId(roleId5);
        user5.setUserName("janedoe");
        user5.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity6 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity6
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity6.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity6.setToken("ABC123");
        resetPasswordTokenEntity6.setUser(user5);

        RoleEntity roleId6 = new RoleEntity();
        roleId6.setId(1L);
        roleId6.setRole("Role");
        roleId6.setUserId(new HashSet<>());

        UserEntity user6 = new UserEntity();
        user6.setEmail("jane.doe@example.org");
        user6.setEmailVerificationToken(emailVerificationToken5);
        user6.setId(1L);
        user6.setIsEnabled(true);
        user6.setPassword("iloveyou");
        user6.setResetPasswordTokenEntity(resetPasswordTokenEntity6);
        user6.setRoleId(roleId6);
        user6.setUserName("janedoe");
        user6.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationTokenEntity2 = new EmailVerificationTokenEntity();
        emailVerificationTokenEntity2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationTokenEntity2.setToken("ABC123");
        emailVerificationTokenEntity2.setUser(user6);
        emailVerificationTokenEntity2.setVerificationTokenId(1L);
        when(emailVerificationTokenMapper.mapToEntity(Mockito.any()))
                .thenReturn(emailVerificationTokenEntity2);
        emailVerificationTokenRepository.save(null);
        verify(emailVerificationTokenJpaRepository).save(Mockito.any());
        verify(emailVerificationTokenMapper).mapToEntity(Mockito.any());
    }
}

