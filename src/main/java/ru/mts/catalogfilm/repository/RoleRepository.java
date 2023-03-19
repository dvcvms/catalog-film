package ru.mts.catalogfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.catalogfilm.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String name);

}
