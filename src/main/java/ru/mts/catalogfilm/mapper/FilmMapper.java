package ru.mts.catalogfilm.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.mts.catalogfilm.dto.FilmDto;
import ru.mts.catalogfilm.entity.FilmEntity;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FilmMapper {
    FilmDto toDto(FilmEntity entity);

    FilmEntity toEntity(FilmDto dto);
}
