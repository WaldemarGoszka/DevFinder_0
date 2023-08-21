package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.RoleService;
import pl.devfinder.domain.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserMapperImpl.class})
@ExtendWith(SpringExtension.class)
class UserMapperDiffBlueTest {
    @MockBean
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;


    /**
     * Method under test: {@link UserMapper#mapFromDTO(UserDTO)}
     */
    @Test
    void testMapFromDTO2() {
        assertNull(userMapper.mapFromDTO(null));
    }

    /**
     * Method under test: {@link UserMapper#mapFromDTO(UserDTO)}
     */
    @Test
    void testMapFromDTO3() {
        when(roleService.findByRole(Mockito.any())).thenReturn(null);
        User actualMapFromDTOResult = userMapper.mapFromDTO(new UserDTO("janedoe", "01234567-89AB-CDEF-FEDC-BA9876543210",
                "jane.doe@example.org", "iloveyou", true, "Role"));
        assertEquals("jane.doe@example.org", actualMapFromDTOResult.getEmail());
        assertEquals("janedoe", actualMapFromDTOResult.getUserName());
        assertNull(actualMapFromDTOResult.getRole());
        assertTrue(actualMapFromDTOResult.getIsEnabled());
        assertNull(actualMapFromDTOResult.getId());
        verify(roleService).findByRole(Mockito.any());
    }

    /**
     * Method under test: {@link UserMapper#mapFromDTO(UserDTO)}
     */
    @Test
    void testMapFromDTO4() {
        when(roleService.findByRole(Mockito.any())).thenReturn(null);
        UserDTO userDTO = mock(UserDTO.class);
        when(userDTO.getIsEnabled()).thenReturn(true);
        when(userDTO.getRole()).thenReturn("Role");
        when(userDTO.getEmail()).thenReturn("jane.doe@example.org");
        when(userDTO.getPassword()).thenReturn("iloveyou");
        when(userDTO.getUserName()).thenReturn("janedoe");
        User actualMapFromDTOResult = userMapper.mapFromDTO(userDTO);
        assertEquals("jane.doe@example.org", actualMapFromDTOResult.getEmail());
        assertEquals("janedoe", actualMapFromDTOResult.getUserName());
        assertNull(actualMapFromDTOResult.getRole());
        assertTrue(actualMapFromDTOResult.getIsEnabled());
        assertNull(actualMapFromDTOResult.getId());
        verify(roleService).findByRole(Mockito.any());
        verify(userDTO).getIsEnabled();
        verify(userDTO).getEmail();
        verify(userDTO).getPassword();
        verify(userDTO).getRole();
        verify(userDTO).getUserName();
    }


}

