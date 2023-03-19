package ru.mts.catalogfilm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mts.catalogfilm.dto.RoleDto;
import ru.mts.catalogfilm.dto.UserDto;

import javax.naming.NameNotFoundException;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final static String NOT_FOUND_ROLE_MESSAGE = "Role not found: add roles into database";

    @Transactional
    public void register(UserDto user) throws NameNotFoundException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        RoleDto role;
        try {
            role = roleService.findByName("ROLE_USER");
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new NameNotFoundException(NOT_FOUND_ROLE_MESSAGE);
        }

        userService.save(user);
        userService.addRole(user, role);
    }
}
