package ru.mts.catalogfilm.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mts.catalogfilm.dto.RoleDto;
import ru.mts.catalogfilm.dto.UserDto;
import ru.mts.catalogfilm.service.RoleService;
import ru.mts.catalogfilm.service.UserService;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties;

    @Override
    public void run(String... args) throws Exception {

        if (userService.checkUserExist(adminProperties.getUsername())) {
            return;
        }

        UserDto admin = new UserDto();
        admin.setUsername(adminProperties.getUsername());
        admin.setEmail(adminProperties.getEmail());
        admin.setPassword(passwordEncoder.encode(adminProperties.getPassword()));

        RoleDto roleUser = roleService.findByName("ROLE_USER");
        RoleDto roleAdmin = roleService.findByName("ROLE_ADMIN");

        admin.setRoles(Set.of(roleUser, roleAdmin));

        userService.save(admin);
    }
}
