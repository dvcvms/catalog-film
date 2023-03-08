package ru.mts.catalogfilm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.catalogfilm.dto.DirectorDto;
import ru.mts.catalogfilm.service.DirectorService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/director")
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorDto> addDirector(@RequestBody DirectorDto directorDto) {
        DirectorDto savedDirector = directorService.saveDirector(directorDto);
        return ResponseEntity.ok(savedDirector);
    }

    @GetMapping
    public ResponseEntity<DirectorDto> getDirector(@RequestParam("directorUuid") UUID directorUuid) {
        DirectorDto director = directorService.getDirector(directorUuid);
        return ResponseEntity.ok(director);
    }
}
