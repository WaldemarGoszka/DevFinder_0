package pl.devfinder.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;
import pl.devfinder.infrastructure.database.entity.ResetPasswordTokenEntity;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.UserEntityMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserRepository.class})
@ExtendWith(SpringExtension.class)
class UserRepositoryDiffBlueTest {
    @MockBean
    private UserEntityMapper userEntityMapper;

    @MockBean
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserRepository#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
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

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setEmailVerificationToken(emailVerificationToken2);
        userEntity.setId(1L);
        userEntity.setIsEnabled(true);
        userEntity.setPassword("iloveyou");
        userEntity.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        userEntity.setRoleId(roleId3);
        userEntity.setUserName("janedoe");
        userEntity.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        when(userJpaRepository.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(userEntityMapper.mapFromEntity(Mockito.<UserEntity>any())).thenReturn(null);
        assertFalse(userRepository.findByEmail("jane.doe@example.org").isPresent());
        verify(userJpaRepository).findByEmail(Mockito.any());
        verify(userEntityMapper).mapFromEntity(Mockito.<UserEntity>any());
    }

    /**
     * Method under test: {@link UserRepository#findById(Long)}
     */
    @Test
    void testFindById() {
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

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setEmailVerificationToken(emailVerificationToken2);
        userEntity.setId(1L);
        userEntity.setIsEnabled(true);
        userEntity.setPassword("iloveyou");
        userEntity.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        userEntity.setRoleId(roleId3);
        userEntity.setUserName("janedoe");
        userEntity.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        when(userJpaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userEntityMapper.mapFromEntity(Mockito.<UserEntity>any())).thenReturn(null);
        assertFalse(userRepository.findById(1L).isPresent());
        verify(userJpaRepository).findById(Mockito.<Long>any());
        verify(userEntityMapper).mapFromEntity(Mockito.<UserEntity>any());
    }

    /**
     * Method under test: {@link UserRepository#save(User)}
     */
    @Test
    void testSave() {
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

        EmailVerificationTokenEntity emailVerificationToken2 = new EmailVerificationTokenEntity();
        emailVerificationToken2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken2.setToken("ABC123");
        emailVerificationToken2.setUser(user3);
        emailVerificationToken2.setVerificationTokenId(1L);

        UserEntity user4 = new UserEntity();
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user4.setId(1L);
        user4.setIsEnabled(true);
        user4.setPassword("iloveyou");
        user4.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user4.setRoleId(new RoleEntity());
        user4.setUserName("janedoe");
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken3 = new EmailVerificationTokenEntity();
        emailVerificationToken3
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken3.setToken("ABC123");
        emailVerificationToken3.setUser(user4);
        emailVerificationToken3.setVerificationTokenId(1L);

        UserEntity user5 = new UserEntity();
        user5.setEmail("jane.doe@example.org");
        user5.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user5.setId(1L);
        user5.setIsEnabled(true);
        user5.setPassword("iloveyou");
        user5.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user5.setRoleId(new RoleEntity());
        user5.setUserName("janedoe");
        user5.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity2 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity2.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity2.setToken("ABC123");
        resetPasswordTokenEntity2.setUser(user5);

        RoleEntity roleId2 = new RoleEntity();
        roleId2.setId(1L);
        roleId2.setRole("Role");
        roleId2.setUserId(new HashSet<>());

        UserEntity user6 = new UserEntity();
        user6.setEmail("jane.doe@example.org");
        user6.setEmailVerificationToken(emailVerificationToken3);
        user6.setId(1L);
        user6.setIsEnabled(true);
        user6.setPassword("iloveyou");
        user6.setResetPasswordTokenEntity(resetPasswordTokenEntity2);
        user6.setRoleId(roleId2);
        user6.setUserName("janedoe");
        user6.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity3 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity3
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity3.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity3.setToken("ABC123");
        resetPasswordTokenEntity3.setUser(user6);

        RoleEntity roleId3 = new RoleEntity();
        roleId3.setId(1L);
        roleId3.setRole("Role");
        roleId3.setUserId(new HashSet<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setEmailVerificationToken(emailVerificationToken2);
        userEntity.setId(1L);
        userEntity.setIsEnabled(true);
        userEntity.setPassword("iloveyou");
        userEntity.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        userEntity.setRoleId(roleId3);
        userEntity.setUserName("janedoe");
        userEntity.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userJpaRepository.saveAndFlush(Mockito.any())).thenReturn(userEntity);

        UserEntity user7 = new UserEntity();
        user7.setEmail("jane.doe@example.org");
        user7.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user7.setId(1L);
        user7.setIsEnabled(true);
        user7.setPassword("iloveyou");
        user7.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user7.setRoleId(new RoleEntity());
        user7.setUserName("janedoe");
        user7.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken4 = new EmailVerificationTokenEntity();
        emailVerificationToken4
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken4.setToken("ABC123");
        emailVerificationToken4.setUser(user7);
        emailVerificationToken4.setVerificationTokenId(1L);

        UserEntity user8 = new UserEntity();
        user8.setEmail("jane.doe@example.org");
        user8.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user8.setId(1L);
        user8.setIsEnabled(true);
        user8.setPassword("iloveyou");
        user8.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user8.setRoleId(new RoleEntity());
        user8.setUserName("janedoe");
        user8.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity4 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity4
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity4.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity4.setToken("ABC123");
        resetPasswordTokenEntity4.setUser(user8);

        RoleEntity roleId4 = new RoleEntity();
        roleId4.setId(1L);
        roleId4.setRole("Role");
        roleId4.setUserId(new HashSet<>());

        UserEntity user9 = new UserEntity();
        user9.setEmail("jane.doe@example.org");
        user9.setEmailVerificationToken(emailVerificationToken4);
        user9.setId(1L);
        user9.setIsEnabled(true);
        user9.setPassword("iloveyou");
        user9.setResetPasswordTokenEntity(resetPasswordTokenEntity4);
        user9.setRoleId(roleId4);
        user9.setUserName("janedoe");
        user9.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken5 = new EmailVerificationTokenEntity();
        emailVerificationToken5
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken5.setToken("ABC123");
        emailVerificationToken5.setUser(user9);
        emailVerificationToken5.setVerificationTokenId(1L);

        UserEntity user10 = new UserEntity();
        user10.setEmail("jane.doe@example.org");
        user10.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user10.setId(1L);
        user10.setIsEnabled(true);
        user10.setPassword("iloveyou");
        user10.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user10.setRoleId(new RoleEntity());
        user10.setUserName("janedoe");
        user10.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken6 = new EmailVerificationTokenEntity();
        emailVerificationToken6
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken6.setToken("ABC123");
        emailVerificationToken6.setUser(user10);
        emailVerificationToken6.setVerificationTokenId(1L);

        UserEntity user11 = new UserEntity();
        user11.setEmail("jane.doe@example.org");
        user11.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user11.setId(1L);
        user11.setIsEnabled(true);
        user11.setPassword("iloveyou");
        user11.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user11.setRoleId(new RoleEntity());
        user11.setUserName("janedoe");
        user11.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity5 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity5
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity5.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity5.setToken("ABC123");
        resetPasswordTokenEntity5.setUser(user11);

        RoleEntity roleId5 = new RoleEntity();
        roleId5.setId(1L);
        roleId5.setRole("Role");
        roleId5.setUserId(new HashSet<>());

        UserEntity user12 = new UserEntity();
        user12.setEmail("jane.doe@example.org");
        user12.setEmailVerificationToken(emailVerificationToken6);
        user12.setId(1L);
        user12.setIsEnabled(true);
        user12.setPassword("iloveyou");
        user12.setResetPasswordTokenEntity(resetPasswordTokenEntity5);
        user12.setRoleId(roleId5);
        user12.setUserName("janedoe");
        user12.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity6 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity6
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity6.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity6.setToken("ABC123");
        resetPasswordTokenEntity6.setUser(user12);

        RoleEntity roleId6 = new RoleEntity();
        roleId6.setId(1L);
        roleId6.setRole("Role");
        roleId6.setUserId(new HashSet<>());

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setEmailVerificationToken(emailVerificationToken5);
        userEntity2.setId(1L);
        userEntity2.setIsEnabled(true);
        userEntity2.setPassword("iloveyou");
        userEntity2.setResetPasswordTokenEntity(resetPasswordTokenEntity6);
        userEntity2.setRoleId(roleId6);
        userEntity2.setUserName("janedoe");
        userEntity2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntityMapper.mapFromEntity(Mockito.<UserEntity>any())).thenReturn(null);
        when(userEntityMapper.mapToEntity(Mockito.<User>any())).thenReturn(userEntity2);
        assertNull(userRepository.save(null));
        verify(userJpaRepository).saveAndFlush(Mockito.any());
        verify(userEntityMapper).mapFromEntity(Mockito.<UserEntity>any());
        verify(userEntityMapper).mapToEntity(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserRepository#findByUserName(String)}
     */
    @Test
    void testFindByUserName() {
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

        EmailVerificationTokenEntity emailVerificationToken2 = new EmailVerificationTokenEntity();
        emailVerificationToken2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken2.setToken("ABC123");
        emailVerificationToken2.setUser(user3);
        emailVerificationToken2.setVerificationTokenId(1L);

        UserEntity user4 = new UserEntity();
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user4.setId(1L);
        user4.setIsEnabled(true);
        user4.setPassword("iloveyou");
        user4.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user4.setRoleId(new RoleEntity());
        user4.setUserName("janedoe");
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        EmailVerificationTokenEntity emailVerificationToken3 = new EmailVerificationTokenEntity();
        emailVerificationToken3
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        emailVerificationToken3.setToken("ABC123");
        emailVerificationToken3.setUser(user4);
        emailVerificationToken3.setVerificationTokenId(1L);

        UserEntity user5 = new UserEntity();
        user5.setEmail("jane.doe@example.org");
        user5.setEmailVerificationToken(new EmailVerificationTokenEntity());
        user5.setId(1L);
        user5.setIsEnabled(true);
        user5.setPassword("iloveyou");
        user5.setResetPasswordTokenEntity(new ResetPasswordTokenEntity());
        user5.setRoleId(new RoleEntity());
        user5.setUserName("janedoe");
        user5.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity2 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity2
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity2.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity2.setToken("ABC123");
        resetPasswordTokenEntity2.setUser(user5);

        RoleEntity roleId2 = new RoleEntity();
        roleId2.setId(1L);
        roleId2.setRole("Role");
        roleId2.setUserId(new HashSet<>());

        UserEntity user6 = new UserEntity();
        user6.setEmail("jane.doe@example.org");
        user6.setEmailVerificationToken(emailVerificationToken3);
        user6.setId(1L);
        user6.setIsEnabled(true);
        user6.setPassword("iloveyou");
        user6.setResetPasswordTokenEntity(resetPasswordTokenEntity2);
        user6.setRoleId(roleId2);
        user6.setUserName("janedoe");
        user6.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        ResetPasswordTokenEntity resetPasswordTokenEntity3 = new ResetPasswordTokenEntity();
        resetPasswordTokenEntity3
                .setExpirationTime(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        resetPasswordTokenEntity3.setPasswordResetTokenId(1L);
        resetPasswordTokenEntity3.setToken("ABC123");
        resetPasswordTokenEntity3.setUser(user6);

        RoleEntity roleId3 = new RoleEntity();
        roleId3.setId(1L);
        roleId3.setRole("Role");
        roleId3.setUserId(new HashSet<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setEmailVerificationToken(emailVerificationToken2);
        userEntity.setId(1L);
        userEntity.setIsEnabled(true);
        userEntity.setPassword("iloveyou");
        userEntity.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        userEntity.setRoleId(roleId3);
        userEntity.setUserName("janedoe");
        userEntity.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userJpaRepository.findByUserName(Mockito.any())).thenReturn(userEntity);
        when(userEntityMapper.mapFromEntity(Mockito.<UserEntity>any())).thenReturn(null);
        assertFalse(userRepository.findByUserName("janedoe").isPresent());
        verify(userJpaRepository).findByUserName(Mockito.any());
        verify(userEntityMapper).mapFromEntity(Mockito.<UserEntity>any());
    }

    /**
     * Method under test: {@link UserRepository#deleteById(Long)}
     */
    @Test
    void testDeleteById() {
        doNothing().when(userJpaRepository).deleteById(Mockito.<Long>any());
        userRepository.deleteById(1L);
        verify(userJpaRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserRepository#update(String, String, Long)}
     */
    @Test
    void testUpdate() {
        doNothing().when(userJpaRepository).update(Mockito.any(), Mockito.any(), Mockito.<Long>any());
        userRepository.update("janedoe", "jane.doe@example.org", 1L);
        verify(userJpaRepository).update(Mockito.any(), Mockito.any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserRepository#findAll()}
     */
    @Test
    void testFindAll() {
        when(userJpaRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userRepository.findAll().isEmpty());
        verify(userJpaRepository).findAll();
    }
}

