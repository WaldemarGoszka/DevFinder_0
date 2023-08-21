package pl.devfinder.api.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.devfinder.api.controller.exception.UserAlreadyExistsException;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;
import pl.devfinder.util.UserDTOFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegistrationRestControllerMockitoTest {
    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Environment environment;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserRegistrationRestController userRegistrationRestController;

    @Test
    public void registerUserTest() {
        // given
        UserDTO userDTO = UserDTOFixtures.someUserCandidateDTO1();
        User user = UserFixtures.someUserCandidate1();

        when(userService.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.mapFromDTO(any(UserDTO.class))).thenReturn(user);
        when(userService.save(any(User.class))).thenReturn(user);

        // when
        ResponseEntity<User> response = userRegistrationRestController.registerUser(userDTO, request);

        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).findByEmail(userDTO.getEmail());
        verify(userMapper, times(1)).mapFromDTO(any(UserDTO.class));
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void registerUserTest_UserAlreadyExists() {
        // given
        UserDTO userDTO = UserDTOFixtures.someUserCandidateDTO1();
        User user = UserFixtures.someUserCandidate1();

        // when
        when(userService.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userRegistrationRestController.registerUser(userDTO, request);
        });

        // then
        verify(userService, times(1)).findByEmail(userDTO.getEmail());
    }


}