package ru.mts.catalogfilm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.catalogfilm.entity.FilmEntity;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<FilmEntity, UUID> {

}
