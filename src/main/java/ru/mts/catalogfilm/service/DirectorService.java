package ru.mts.catalogfilm.service;


import ru.mts.catalogfilm.dto.DirectorDto;

import java.util.UUID;

public interface DirectorService {
    DirectorDto getDirector(UUID directorUuid);

    DirectorDto saveDirector(DirectorDto director);
}
