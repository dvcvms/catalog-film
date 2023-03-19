package ru.mts.catalogfilm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.mts.catalogfilm.dto.RoleDto;
import ru.mts.catalogfilm.entity.RoleEntity;
import ru.mts.catalogfilm.mapper.RoleMapper;
import ru.mts.catalogfilm.repository.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    public RoleDto findByName(String name) throws ChangeSetPersister.NotFoundException {
        Optional<RoleEntity> roleIntoDatabase = roleRepository.findByName(name);

        if (roleIntoDatabase.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        return mapper.toDto(roleIntoDatabase.get());
    }

    public RoleDto save(RoleDto role) {
        return mapper.toDto(roleRepository.save(mapper.toEntity(role)));
    }
}
