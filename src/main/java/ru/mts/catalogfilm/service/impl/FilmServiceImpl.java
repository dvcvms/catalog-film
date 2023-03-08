package ru.mts.catalogfilm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.mts.catalogfilm.dto.FilmDto;
import ru.mts.catalogfilm.entity.FilmEntity;
import ru.mts.catalogfilm.mapper.FilmMapper;
import ru.mts.catalogfilm.repository.FilmRepository;
import ru.mts.catalogfilm.service.FilmService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper mapper;

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public FilmDto getFilm(UUID filmUuid) {
        Optional<FilmEntity> filmIntoDatabase = filmRepository.findById(filmUuid);

        if (filmIntoDatabase.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        return mapper.toDto(filmIntoDatabase.get());
    }

    @Override
    public FilmDto saveFilm(FilmDto film) {
        return mapper.toDto(filmRepository.save(mapper.toEntity(film)));
    }
}

