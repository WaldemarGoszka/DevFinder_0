package pl.devfinder.business;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffBlueTest {
    @MockBean
    private CandidateService candidateService;

    @MockBean
    private EmailVerificationTokenDAO emailVerificationTokenDAO;

    @MockBean
    private EmployerService employerService;

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
        when(userDAO.findByEmail(Mockito.any())).thenReturn(emptyResult);
        Optional<User> actualFindByEmailResult = userService.findByEmail("jane.doe@example.org");
        assertSame(emptyResult, actualFindByEmailResult);
        assertFalse(actualFindByEmailResult.isPresent());
        verify(userDAO).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#findByEmail(String)}
     */
    @Test
    void testFindByEmail2() {
        when(userDAO.findByEmail(Mockito.any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.findByEmail("jane.doe@example.org"));
        verify(userDAO).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#findByUserName(String)}
     */
    @Test
    void testFindByUserName() {
        Optional<User> emptyResult = Optional.empty();
        when(userDAO.findByUserName(Mockito.any())).thenReturn(emptyResult);
        Optional<User> actualFindByUserNameResult = userService.findByUserName("janedoe");
        assertSame(emptyResult, actualFindByUserNameResult);
        assertFalse(actualFindByUserNameResult.isPresent());
        verify(userDAO).findByUserName(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#findByUserName(String)}
     */
    @Test
    void testFindByUserName2() {
        when(userDAO.findByUserName(Mockito.any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.findByUserName("janedoe"));
        verify(userDAO).findByUserName(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave() {
        when(userDAO.save(Mockito.any())).thenReturn(null);
        assertNull(userService.save(null));
        verify(userDAO).save(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave2() {
        when(userDAO.save(Mockito.any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.save(null));
        verify(userDAO).save(Mockito.any());
    }


    /**
     * Method under test: {@link UserService#updateUser(String, String, Long)}
     */
    @Test
    void testUpdateUser() {
        doNothing().when(userDAO).update(Mockito.any(), Mockito.any(), Mockito.<Long>any());
        userService.updateUser("janedoe", "jane.doe@example.org", 1L);
        verify(userDAO).update(Mockito.any(), Mockito.any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(String, String, Long)}
     */
    @Test
    void testUpdateUser2() {
        doThrow(new NotFoundException("An error occurred")).when(userDAO)
                .update(Mockito.any(), Mockito.any(), Mockito.<Long>any());
        assertThrows(NotFoundException.class, () -> userService.updateUser("janedoe", "jane.doe@example.org", 1L));
        verify(userDAO).update(Mockito.any(), Mockito.any(), Mockito.<Long>any());
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

