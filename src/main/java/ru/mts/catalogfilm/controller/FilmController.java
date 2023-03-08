package ru.mts.catalogfilm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.catalogfilm.dto.FilmDto;
import ru.mts.catalogfilm.service.FilmService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<FilmDto> addFilm(@RequestBody FilmDto film) {
        FilmDto savedFilm = filmService.saveFilm(film);
        return ResponseEntity.ok(savedFilm);
    }

    @GetMapping
    public ResponseEntity<FilmDto> getFilm(@RequestParam("filmUuid") UUID filmUuid) {
        FilmDto film = filmService.getFilm(filmUuid);
        return ResponseEntity.ok(film);
    }
}
