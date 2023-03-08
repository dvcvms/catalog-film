package ru.mts.catalogfilm.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.mts.catalogfilm.dto.DirectorDto;
import ru.mts.catalogfilm.entity.DirectorEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DirectorMapper {
    DirectorDto toDto(DirectorEntity entity);

    DirectorEntity toEntity(DirectorDto dto);
}
