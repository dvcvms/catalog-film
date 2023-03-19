package ru.mts.catalogfilm.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.mts.catalogfilm.dto.UserDto;
import ru.mts.catalogfilm.entity.UserEntity;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);
}
