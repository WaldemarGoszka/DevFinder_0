package pl.devfinder.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.User;

@Controller
@AllArgsConstructor
public class UserRegistrationController {

  UserService userService;
  UserMapper userMapper;
  @GetMapping("/register")
  public String getRegistrationPage(Model model){
    model.addAttribute("user", new UserDTO());
    model.addAttribute("candidateEnum", Keys.Role.CANDIDATE.getName());
    model.addAttribute("employerEnum", Keys.Role.EMPLOYER.getName());
    return "register";
  }

  @PostMapping("/register/save")
  public String postRegistrationPage(@Valid @ModelAttribute("user") UserDTO user,
                             BindingResult result,
                             Model model){
    User existing = userService.findByEmail(user.getEmail());
    if (existing != null) {
      result.rejectValue("email"
              , null
              , "There is already an account registered with that email");
    }

//    if(existing != null && existing.getEmail() != null && !existing.getEmail().isEmpty()){
//      result.rejectValue("email", null,
//              "There is already an account registered with the same email");
//    }


    if (result.hasErrors()) {
      model.addAttribute("user", user);
      return "register";
    }
    userService.save(user);
    return "redirect:/register?success";
  }

}
