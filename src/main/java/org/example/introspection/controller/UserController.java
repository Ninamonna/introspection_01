package org.example.introspection.controller;

import org.example.introspection.domain.Role;
import org.example.introspection.domain.User;
import org.example.introspection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')") // проверяет права администратора для всех методов
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")//вкладывается в адрес значение @PathVariable user
    public String userEditForm(@PathVariable User user, Model model) {
        //@PathVariable передает параметр через адрес запроса
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            // @RequestParam для извлечения параметров запроса, параметров формы и даже файлов из запроса.
            @RequestParam String username,
            @RequestParam Map<String,String> form, //Список полей которые передаются в форме под флажком
            @RequestParam("userId") User user) {

        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String editProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String email,
            @RequestParam String password
    ) {
        userService.updateProfile(user,email,password);

        return "redirect:/user/profile";
    }
}
