package ru.mts.catalogfilm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mts.catalogfilm.dto.UserDto;
import ru.mts.catalogfilm.service.RegistrationService;
import ru.mts.catalogfilm.util.UserValidator;

import javax.naming.NameNotFoundException;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") UserDto userDto) {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult bindingResult) throws NameNotFoundException {
        userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationService.register(userDto);

        return "redirect:/auth/login";
    }
}
