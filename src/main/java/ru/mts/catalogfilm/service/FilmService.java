package ru.mts.catalogfilm.service;


import ru.mts.catalogfilm.dto.FilmDto;

import java.util.UUID;

public interface FilmService {

    FilmDto getFilm(UUID filmUuid);

    FilmDto saveFilm(FilmDto film);
}
