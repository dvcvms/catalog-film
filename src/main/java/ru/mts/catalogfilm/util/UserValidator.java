package ru.mts.catalogfilm.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mts.catalogfilm.dto.UserDto;
import ru.mts.catalogfilm.entity.UserEntity;
import ru.mts.catalogfilm.service.UserService;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    private final static String USER_ALREADY_EXIST_MESSAGE = "User with this username already exists. Please think up another username.";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (userService.checkUserExist(user.getUsername())) {
            errors.rejectValue("username", "", USER_ALREADY_EXIST_MESSAGE);
        }
    }
}
