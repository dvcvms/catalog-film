package ru.mts.catalogfilm.dto;

import lombok.Data;
import ru.mts.catalogfilm.entity.FilmEntity;

import java.util.List;
import java.util.UUID;

@Data
public class DirectorDto {
    private UUID uuid;
    private String name;
    private Integer age;
    private String country;
    private List<FilmEntity> filmList;
}
