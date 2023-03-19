package ru.mts.catalogfilm.service;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.mts.catalogfilm.dto.RoleDto;
import ru.mts.catalogfilm.dto.UserDto;
import ru.mts.catalogfilm.entity.UserEntity;
import ru.mts.catalogfilm.mapper.RoleMapper;
import ru.mts.catalogfilm.mapper.UserMapper;
import ru.mts.catalogfilm.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserDto getUser(String username) throws ChangeSetPersister.NotFoundException {
        Optional<UserEntity> userIntoDatabase = userRepository.findByUsername(username);

        if (userIntoDatabase.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        return userMapper.toDto(userIntoDatabase.get());
    }

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    public UserDto addRole(UserDto user, RoleDto role) {
        Optional<UserEntity> userIntoDatabase = userRepository.findByUsername(user.getUsername());
        if (userIntoDatabase.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        userIntoDatabase.get().addRole(roleMapper.toEntity(role));
        return userMapper.toDto(userRepository.save(userIntoDatabase.get()));
    }

    public UserDto save(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public boolean checkUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
