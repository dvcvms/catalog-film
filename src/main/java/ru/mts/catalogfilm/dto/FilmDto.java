package ru.mts.catalogfilm.dto;

import lombok.Data;
import ru.mts.catalogfilm.constans.GenreEnum;

import java.util.UUID;

@Data
public class FilmDto {
    private UUID uuid;
    private String title;
    private GenreEnum genre;
    private Integer rating;
}
