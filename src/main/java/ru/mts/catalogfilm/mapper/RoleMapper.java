package ru.mts.catalogfilm.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.mts.catalogfilm.dto.RoleDto;
import ru.mts.catalogfilm.entity.RoleEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper {
    RoleDto toDto(RoleEntity entity);

    RoleEntity toEntity(RoleDto dto);
}
