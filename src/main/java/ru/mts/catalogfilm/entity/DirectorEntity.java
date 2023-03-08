package ru.mts.catalogfilm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;
    private Integer age;
    private String country;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FilmEntity> filmList;

}
