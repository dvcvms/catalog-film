package catalogfilm.service.impl;

import catalogfilm.model.Film;
import catalogfilm.repository.FilmRepository;
import catalogfilm.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public Film getFilm(UUID filmUuid) {
        Optional<Film> filmOptional = filmRepository.findById(filmUuid);
        return filmOptional.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }
}

