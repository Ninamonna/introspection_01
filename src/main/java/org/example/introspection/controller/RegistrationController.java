package org.example.introspection.controller;

import javafx.print.Collation;
import org.example.introspection.domain.User;
import org.example.introspection.domain.dto.CaptchaResponseDTO;
import org.example.introspection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;


@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    private UserService userService;
    @Value("${recaptcha.secret}")
    private String secret;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam ("g-recaptcha-response") String captchaResponse,
            @RequestParam ("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);

        CaptchaResponseDTO response =
                restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);

        if(!response.isSuccess()) {
            model.addAttribute("captchaError", "Пройдите проверку");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        if(isConfirmEmpty) {
            model.addAttribute("password2Error","Поле не должно быть пустым");

        }

        if(user.getPassword()!= null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли должны совпадать");
        }

        if(isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            return "registration";
        }

        if(!userService.addUser(user)) {
            model.addAttribute("usernameError","Этот пользователь уже существует, попробуйте ввести другое имя!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivate = userService.activateUser(code);
        if (isActivate) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Пользователь успешно активирован!");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message","Код активации не найден");
        }
        return "login";
    }

}
