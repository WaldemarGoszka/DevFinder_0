package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Role;
import pl.devfinder.infrastructure.database.entity.RoleEntity;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RoleEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class RoleEntityMapperImplDiffBlueTest {
    @Autowired
    private RoleEntityMapperImpl roleEntityMapperImpl;

    /**
     * Method under test: {@link RoleEntityMapperImpl#mapFromEntity(RoleEntity)}
     */
    @Test
    void testMapFromEntity() {
        RoleEntity userEntity = new RoleEntity();
        userEntity.setId(1L);
        userEntity.setRole("Role");
        userEntity.setUserId(new HashSet<>());
        Role actualMapFromEntityResult = roleEntityMapperImpl.mapFromEntity(userEntity);
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertNull(actualMapFromEntityResult.getUserId());
        assertEquals("Role", actualMapFromEntityResult.getRole());
    }

    /**
     * Method under test: {@link RoleEntityMapperImpl#mapFromEntity(RoleEntity)}
     */
    @Test
    void testMapFromEntity2() {
        RoleEntity userEntity = mock(RoleEntity.class);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getRole()).thenReturn("Role");
        doNothing().when(userEntity).setId(Mockito.<Long>any());
        doNothing().when(userEntity).setRole(Mockito.any());
        doNothing().when(userEntity).setUserId(Mockito.any());
        userEntity.setId(1L);
        userEntity.setRole("Role");
        userEntity.setUserId(new HashSet<>());
        Role actualMapFromEntityResult = roleEntityMapperImpl.mapFromEntity(userEntity);
        assertEquals(1L, actualMapFromEntityResult.getId().longValue());
        assertNull(actualMapFromEntityResult.getUserId());
        assertEquals("Role", actualMapFromEntityResult.getRole());
        verify(userEntity).getId();
        verify(userEntity).getRole();
        verify(userEntity).setId(Mockito.<Long>any());
        verify(userEntity).setRole(Mockito.any());
        verify(userEntity).setUserId(Mockito.any());
    }
}

