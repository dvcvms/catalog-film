package ru.mts.catalogfilm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import ru.mts.catalogfilm.constans.GenreEnum;

import java.util.UUID;

@Data
@Entity
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String title;
    private GenreEnum genre;
    private Integer rating;

}
