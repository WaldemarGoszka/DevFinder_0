package pl.devfinder.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.UserService;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

//    @GetMapping
//    public String getUsers(Model model){
//        model.addAttribute("users", userService.getAllUsers());
//        return "users";
//    }
    @GetMapping("/my_profile")
    public String getMyProfilePage(Model model) {
        return "user_my_profile";
        // sprawdzenie czy użytkownik jest zalogowany, i pobranie z bazu jego dancyh
    }
    @GetMapping("/settings")
    public String getSettingsPage(Model model) {
        return "user_settings";
        // sprawdzenie czy użytkownik jest zalogowany, i pobranie z bazu jego dancyh
    }


    @GetMapping("/edit/{id}")
    public String getUpdatePage(@PathVariable("id") Long id, Model model){
        UserDTO user = userMapper.mapToDTO(userService.findById(id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, UserDTO userDTO){
        userService.updateUser(userDTO.getUserName(), userDTO.getEmail(), id);
        return "redirect:/users?update_success";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/users?delete_success";
    }
}