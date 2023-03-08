package ru.mts.catalogfilm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.catalogfilm.entity.DirectorEntity;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<DirectorEntity, UUID> {

}
