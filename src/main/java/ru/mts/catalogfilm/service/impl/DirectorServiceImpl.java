package ru.mts.catalogfilm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.mts.catalogfilm.dto.DirectorDto;
import ru.mts.catalogfilm.entity.DirectorEntity;
import ru.mts.catalogfilm.mapper.DirectorMapper;
import ru.mts.catalogfilm.repository.DirectorRepository;
import ru.mts.catalogfilm.service.DirectorService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper mapper;

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public DirectorDto getDirector(UUID directorUuid) {
        Optional<DirectorEntity> directorIntoDatabase = directorRepository.findById(directorUuid);

        if (directorIntoDatabase.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        return mapper.toDto(directorIntoDatabase.get());
    }

    @Override
    public DirectorDto saveDirector(DirectorDto director) {
        return mapper.toDto(directorRepository.save(mapper.toEntity(director)));
    }
}
