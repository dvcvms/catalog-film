package catalogfilm.repository;


import catalogfilm.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {

}
