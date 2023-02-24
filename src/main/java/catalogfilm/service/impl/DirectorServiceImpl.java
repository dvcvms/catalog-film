package catalogfilm.service.impl;

import catalogfilm.model.Director;
import catalogfilm.repository.DirectorRepository;
import catalogfilm.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public Director getDirector(UUID directorUuid) {
        Optional<Director> directorOptional = directorRepository.findById(directorUuid);
        return directorOptional.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }
}
