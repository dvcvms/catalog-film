package catalogfilm.service;


import catalogfilm.model.Director;

import java.util.UUID;

public interface DirectorService {
    Director getDirector(UUID directorUuid);

    Director saveDirector(Director director);
}
