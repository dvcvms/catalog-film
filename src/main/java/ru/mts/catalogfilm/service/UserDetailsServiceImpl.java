package ru.mts.catalogfilm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mts.catalogfilm.dto.UserDto;
import ru.mts.catalogfilm.security.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) {
        UserDto userIntoDatabase;

        try {
            userIntoDatabase = userService.getUser(username);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new UsernameNotFoundException("User not found", e);
        }

        return new UserDetailsImpl(userIntoDatabase);
    }
}
