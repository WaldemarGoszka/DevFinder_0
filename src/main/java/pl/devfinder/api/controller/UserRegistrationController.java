package pl.devfinder.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.security.event.RegistrationCompleteEvent;
import pl.devfinder.infrastructure.security.event.RegistrationCompleteEventListener;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class UserRegistrationController {

  private final UserService userService;
  private final UserMapper userMapper;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final RegistrationCompleteEventListener registrationCompleteEventListener;


  @GetMapping("/register_page")
  public String getRegistrationPage(Model model){
    model.addAttribute("user", new UserDTO());
    model.addAttribute("candidateEnum", Keys.Role.CANDIDATE.getName());
    model.addAttribute("employerEnum", Keys.Role.EMPLOYER.getName());
    return "register";
  }

  @PostMapping("/register_save")
  public String postRegistrationPage(@Valid @ModelAttribute("user") UserDTO userDTO,
                                     BindingResult result,
                                     Model model, HttpServletRequest request){
    User existing = userService.findByEmail(userDTO.getEmail());
    if (existing != null) {
      result.rejectValue("email"
              , null
              , "There is already an account registered with that email");
    }

    if (result.hasErrors()) {
      model.addAttribute("user", userDTO);
      return "register";
    }
    User user = userService.save(userDTO);
    // send verification email:
    applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, Utility.getApplicationUrl(request)));
    return "redirect:/register/register?success";
  }

  @GetMapping("/verify_email")
  public String verifyEmail(@RequestParam("token") String token) {
    Optional<VerificationToken> theToken = tokenService.findByToken(token);
    if (theToken.isPresent() && theToken.get().getUser().isEnabled()) {
      return "redirect:/login?verified";
    }
    String verificationResult = tokenService.validateToken(token);
    switch (verificationResult.toLowerCase()) {
      case "expired":
        return "redirect:/error?expired";
      case "valid":
        return "redirect:/login?valid";
      default:
        return "redirect:/error?invalid";
    }
  }

}
