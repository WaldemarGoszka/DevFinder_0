package pl.devfinder.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.FileService;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(UserController.BASE_PATH)
@RequiredArgsConstructor
public class UserController {
    public static final String BASE_PATH = "/user";
    public static final String SETTINGS = "/settings";
    public static final String CHANGE_PASSWORD = "/change-password";
    public static final String DELETE = "/delete";
    public static final String DEFAULT_PHOTO_PATH = "/img/user.jpg";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CandidateService candidateService;
    private final EmployerService employerService;
    private final FileService fileService;

    @GetMapping(SETTINGS)
    public String getSettingsPage(Model model, Authentication authentication) {
        Optional<User> user = this.putUserDataToModel(authentication, userService, model);
        this.setUserPhotoToModel(model, user);
        user.orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        return "settings";
    }


    @DeleteMapping(DELETE)
    public String deleteUser(Model model, Authentication authentication) {
        log.info("Process delete account for user: [{}]", authentication.getName());
        User user = this.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        userService.deleteUser(user);
        return "redirect:/logout";
    }

    @PatchMapping(CHANGE_PASSWORD)
    public String getChangePassword(@RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("newPassword") String newPassword,
                                    @RequestParam("repeatNewPassword") String repeatPassword,
                                    Model model, Authentication authentication) {
        log.info("Process change password to user: [{}]", authentication.getName());
        User user = this.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())
                && !passwordEncoder.matches(newPassword, oldPassword)
                && !passwordEncoder.matches(newPassword, user.getPassword())
                && repeatPassword.equals(newPassword)) {
            User userToSave = user.withPassword(passwordEncoder.encode(newPassword));
            userService.save(userToSave);
            return "redirect:/user/settings?change_password_success";
        } else {
            return "redirect:/user/settings?change_password_fail";
        }
    }

    public void setUserPhotoToModel(Model model, Optional<User> user) {
        if (user.isPresent()) {
            if (user.get().getRole().getRole().equals(Keys.Role.CANDIDATE.getName())) {
                Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.get().getUserUuid());
                if (candidate.isPresent() && Objects.nonNull(candidate.get().getPhotoFilename())) {
                    model.addAttribute("photoDir", fileService.getUserPhotoPath(candidate.get().getCandidateUuid(), candidate.get().getPhotoFilename()));
                } else {
                    model.addAttribute("photoDir", UserController.DEFAULT_PHOTO_PATH);
                }
            }
            if (user.get().getRole().getRole().equals(Keys.Role.EMPLOYER.getName())) {
                Optional<Employer> employer = employerService.findByEmployerUuid(user.get().getUserUuid());
                if (employer.isPresent() && Objects.nonNull(employer.get().getLogoFilename())) {
                    model.addAttribute("photoDir", fileService.getUserPhotoPath(employer.get().getEmployerUuid(), employer.get().getLogoFilename()));
                } else {
                    model.addAttribute("photoDir", UserController.DEFAULT_PHOTO_PATH);
                }
            }
        }
    }

    public Optional<User> putUserDataToModel(Authentication authentication, UserService userService, Model model) {
        if (authentication != null) {
            Optional<User> user = userService.findByEmail(authentication.getName());
            if (user.isEmpty()) {
                return Optional.empty();
            }
            model.addAttribute("user", user.get());
            return user;
        }
        return Optional.empty();
    }

}
