package catalogfilm.controller;


import catalogfilm.model.Film;
import catalogfilm.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<?> addFilm(@RequestBody Film film) {
        Film resultFilm = filmService.saveFilm(film);
        return ResponseEntity.ok(resultFilm);
    }

    @GetMapping
    public ResponseEntity<?> getFilm(@RequestParam("filmUuid") UUID filmUuid) {
        Film film = filmService.getFilm(filmUuid);
        return ResponseEntity.ok(film);
    }
}
