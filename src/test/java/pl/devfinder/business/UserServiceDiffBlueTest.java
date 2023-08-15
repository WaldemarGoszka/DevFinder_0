package pl.devfinder.business;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.business.dao.UserDAO;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffBlueTest {
    @MockBean
    private EmailVerificationTokenDAO emailVerificationTokenDAO;

    @MockBean
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        Optional<User> emptyResult = Optional.empty();
        when(userDAO.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        Optional<User> actualFindByEmailResult = userService.findByEmail("jane.doe@example.org");
        assertSame(emptyResult, actualFindByEmailResult);
        assertFalse(actualFindByEmailResult.isPresent());
        verify(userDAO).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#findByEmail(String)}
     */
    @Test
    void testFindByEmail2() {
        when(userDAO.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.findByEmail("jane.doe@example.org"));
        verify(userDAO).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#findByUserName(String)}
     */
    @Test
    void testFindByUserName() {
        Optional<User> emptyResult = Optional.empty();
        when(userDAO.findByUserName(Mockito.<String>any())).thenReturn(emptyResult);
        Optional<User> actualFindByUserNameResult = userService.findByUserName("janedoe");
        assertSame(emptyResult, actualFindByUserNameResult);
        assertFalse(actualFindByUserNameResult.isPresent());
        verify(userDAO).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#findByUserName(String)}
     */
    @Test
    void testFindByUserName2() {
        when(userDAO.findByUserName(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.findByUserName("janedoe"));
        verify(userDAO).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave() {
        when(userDAO.save(Mockito.<User>any())).thenReturn(null);
        assertNull(userService.save(null));
        verify(userDAO).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave2() {
        when(userDAO.save(Mockito.<User>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.save(null));
        verify(userDAO).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#deleteUserById(Long)}
     */
    @Test
    void testDeleteUserById() {
        when(userDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        doNothing().when(userDAO).deleteById(Mockito.<Long>any());
        userService.deleteUserById(1L);
        verify(userDAO).findById(Mockito.<Long>any());
        verify(userDAO).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUserById(Long)}
     */
    @Test
    void testDeleteUserById2() {
        when(userDAO.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.deleteUserById(1L));
        verify(userDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(String, String, Long)}
     */
    @Test
    void testUpdateUser() {
        doNothing().when(userDAO).update(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
        userService.updateUser("janedoe", "jane.doe@example.org", 1L);
        verify(userDAO).update(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(String, String, Long)}
     */
    @Test
    void testUpdateUser2() {
        doThrow(new NotFoundException("An error occurred")).when(userDAO)
                .update(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
        assertThrows(NotFoundException.class, () -> userService.updateUser("janedoe", "jane.doe@example.org", 1L));
        verify(userDAO).update(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#findById(Long)}
     */
    @Test
    void testFindById() {
        when(userDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.findById(1L));
        verify(userDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#findById(Long)}
     */
    @Test
    void testFindById2() {
        when(userDAO.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.findById(1L));
        verify(userDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(userDAO.findAll()).thenReturn(userList);
        List<User> actualFindAllResult = userService.findAll();
        assertSame(userList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(userDAO).findAll();
    }
}

