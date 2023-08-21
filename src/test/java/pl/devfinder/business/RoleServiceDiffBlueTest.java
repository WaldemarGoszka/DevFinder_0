package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.RoleDAO;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RoleService.class})
@ExtendWith(SpringExtension.class)
class RoleServiceDiffBlueTest {
    @MockBean
    private RoleDAO roleDAO;

    @Autowired
    private RoleService roleService;

    /**
     * Method under test: {@link RoleService#findByRole(String)}
     */
    @Test
    void testFindByRole() {
        when(roleDAO.findByRole(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> roleService.findByRole("Role"));
        verify(roleDAO).findByRole(Mockito.any());
    }

    /**
     * Method under test: {@link RoleService#findByRole(String)}
     */
    @Test
    void testFindByRole2() {
        when(roleDAO.findByRole(Mockito.any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> roleService.findByRole("Role"));
        verify(roleDAO).findByRole(Mockito.any());
    }
}

