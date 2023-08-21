package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;
import pl.devfinder.infrastructure.database.entity.ResetPasswordTokenEntity;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class UserEntityMapperImplDiffBlueTest {
    @Autowired
    private UserEntityMapperImpl userEntityMapperImpl;

    /**
     * Method under test: {@link UserEntityMapperImpl#mapFromEntity(RoleEntity)}
     */
    @Test
    void testMapFromEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        Role actualMapFromEntityResult = userEntityMapperImpl.mapFromEntity(roleEntity);
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertNull(actualMapFromEntityResult.getUserId());
        assertEquals("Role", actualMapFromEntityResult.getRole());
    }

    /**
     * Method under test: {@link UserEntityMapperImpl#mapFromEntity(RoleEntity)}
     */
    @Test
    void testMapFromEntity2() {
        RoleEntity roleEntity = mock(RoleEntity.class);
        when(roleEntity.getId()).thenReturn(1L);
        when(roleEntity.getRole()).thenReturn("Role");
        doNothing().when(roleEntity).setId(Mockito.<Long>any());
        doNothing().when(roleEntity).setRole(Mockito.any());
        doNothing().when(roleEntity).setUserId(Mockito.any());
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        Role actualMapFromEntityResult = userEntityMapperImpl.mapFromEntity(roleEntity);
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertNull(actualMapFromEntityResult.getUserId());
        assertEquals("Role", actualMapFromEntityResult.getRole());
        verify(roleEntity).getId();
        verify(roleEntity).getRole();
        verify(roleEntity).setId(Mockito.<Long>any());
        verify(roleEntity).setRole(Mockito.any());
        verify(roleEntity).setUserId(Mockito.any());
    }

    /**
     * Method under test: {@link UserEntityMapperImpl#mapFromEntity(UserEntity)}
     */
    @Test
    void testMapFromEntity3() {
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
        User actualMapFromEntityResult = userEntityMapperImpl.mapFromEntity(userEntity);
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getUserUuid());
        assertEquals("janedoe", actualMapFromEntityResult.getUserName());
        assertEquals("iloveyou", actualMapFromEntityResult.getPassword());
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertTrue(actualMapFromEntityResult.getIsEnabled());
        Role role = actualMapFromEntityResult.getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
    }

    /**
     * Method under test: {@link UserEntityMapperImpl#mapFromEntity(UserEntity)}
     */
    @Test
    void testMapFromEntity4() {
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

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        doNothing().when(userEntity).setEmail(Mockito.any());
        doNothing().when(userEntity).setEmailVerificationToken(Mockito.any());
        doNothing().when(userEntity).setId(Mockito.<Long>any());
        doNothing().when(userEntity).setIsEnabled(Mockito.<Boolean>any());
        doNothing().when(userEntity).setPassword(Mockito.any());
        doNothing().when(userEntity).setResetPasswordTokenEntity(Mockito.any());
        doNothing().when(userEntity).setRoleId(Mockito.any());
        doNothing().when(userEntity).setUserName(Mockito.any());
        doNothing().when(userEntity).setUserUuid(Mockito.any());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setEmailVerificationToken(emailVerificationToken2);
        userEntity.setId(1L);
        userEntity.setIsEnabled(true);
        userEntity.setPassword("iloveyou");
        userEntity.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        userEntity.setRoleId(roleId3);
        userEntity.setUserName("janedoe");
        userEntity.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        User actualMapFromEntityResult = userEntityMapperImpl.mapFromEntity(userEntity);
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getUserUuid());
        assertEquals("janedoe", actualMapFromEntityResult.getUserName());
        assertEquals("iloveyou", actualMapFromEntityResult.getPassword());
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertTrue(actualMapFromEntityResult.getIsEnabled());
        Role role = actualMapFromEntityResult.getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(userEntity).setEmail(Mockito.any());
        verify(userEntity).setEmailVerificationToken(Mockito.any());
        verify(userEntity).setId(Mockito.<Long>any());
        verify(userEntity).setIsEnabled(Mockito.<Boolean>any());
        verify(userEntity).setPassword(Mockito.any());
        verify(userEntity).setResetPasswordTokenEntity(Mockito.any());
        verify(userEntity).setRoleId(Mockito.any());
        verify(userEntity).setUserName(Mockito.any());
        verify(userEntity).setUserUuid(Mockito.any());
    }

    /**
     * Method under test: {@link UserEntityMapperImpl#mapFromEntity(UserEntity)}
     */
    @Test
    void testMapFromEntity5() {
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
        RoleEntity roleEntity = mock(RoleEntity.class);
        when(roleEntity.getId()).thenReturn(1L);
        when(roleEntity.getRole()).thenReturn("Role");
        doNothing().when(roleEntity).setId(Mockito.<Long>any());
        doNothing().when(roleEntity).setRole(Mockito.any());
        doNothing().when(roleEntity).setUserId(Mockito.any());
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        doNothing().when(userEntity).setEmail(Mockito.any());
        doNothing().when(userEntity).setEmailVerificationToken(Mockito.any());
        doNothing().when(userEntity).setId(Mockito.<Long>any());
        doNothing().when(userEntity).setIsEnabled(Mockito.<Boolean>any());
        doNothing().when(userEntity).setPassword(Mockito.any());
        doNothing().when(userEntity).setResetPasswordTokenEntity(Mockito.any());
        doNothing().when(userEntity).setRoleId(Mockito.any());
        doNothing().when(userEntity).setUserName(Mockito.any());
        doNothing().when(userEntity).setUserUuid(Mockito.any());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setEmailVerificationToken(emailVerificationToken2);
        userEntity.setId(1L);
        userEntity.setIsEnabled(true);
        userEntity.setPassword("iloveyou");
        userEntity.setResetPasswordTokenEntity(resetPasswordTokenEntity3);
        userEntity.setRoleId(roleId3);
        userEntity.setUserName("janedoe");
        userEntity.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        User actualMapFromEntityResult = userEntityMapperImpl.mapFromEntity(userEntity);
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getUserUuid());
        assertEquals("janedoe", actualMapFromEntityResult.getUserName());
        assertEquals("iloveyou", actualMapFromEntityResult.getPassword());
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertTrue(actualMapFromEntityResult.getIsEnabled());
        Role role = actualMapFromEntityResult.getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(userEntity).setEmail(Mockito.any());
        verify(userEntity).setEmailVerificationToken(Mockito.any());
        verify(userEntity).setId(Mockito.<Long>any());
        verify(userEntity).setIsEnabled(Mockito.<Boolean>any());
        verify(userEntity).setPassword(Mockito.any());
        verify(userEntity).setResetPasswordTokenEntity(Mockito.any());
        verify(userEntity).setRoleId(Mockito.any());
        verify(userEntity).setUserName(Mockito.any());
        verify(userEntity).setUserUuid(Mockito.any());
        verify(roleEntity).getId();
        verify(roleEntity).getRole();
        verify(roleEntity).setId(Mockito.<Long>any());
        verify(roleEntity).setRole(Mockito.any());
        verify(roleEntity).setUserId(Mockito.any());
    }

    /**
     * Method under test: {@link UserEntityMapperImpl#mapToEntity(Role)}
     */
    @Test
    void testMapToEntity() {
        assertNull(userEntityMapperImpl.mapToEntity((Role) null));
        assertNull(userEntityMapperImpl.mapToEntity((User) null));
    }

    /**
     * Method under test: {@link UserEntityMapperImpl#userSetToUserEntitySet(Set)}
     */
    @Test
    void testUserSetToUserEntitySet() {
        assertTrue(userEntityMapperImpl.userSetToUserEntitySet(new HashSet<>()).isEmpty());
    }
}

