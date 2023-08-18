package pl.devfinder.api.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

@Slf4j
@RestController
@RequestMapping(UserRestController.BASE_PATH)
@RequiredArgsConstructor
public class UserRestController {
    public static final String BASE_PATH = "/api/user";

    public static final String CHANGE_PASSWORD = "/change-password";
    public static final String DELETE = "/delete";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        log.info("Process delete account for user: [{}]", authentication.getName());
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(CHANGE_PASSWORD)
    public ResponseEntity<?> getChangePassword(@RequestParam("oldPassword") String oldPassword,
                                               @RequestParam("newPassword") String newPassword,
                                               @RequestParam("repeatNewPassword") String repeatPassword,
                                               Authentication authentication) {
        log.info("Process change password to user: [{}]", authentication.getName());
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())
                && !passwordEncoder.matches(newPassword, oldPassword)
                && !passwordEncoder.matches(newPassword, user.getPassword())
                && repeatPassword.equals(newPassword)) {
            User userToSave = user.withPassword(passwordEncoder.encode(newPassword));
            userService.save(userToSave);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
