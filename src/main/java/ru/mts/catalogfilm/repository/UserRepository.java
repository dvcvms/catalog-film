package ru.mts.catalogfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.catalogfilm.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

}
