package ru.mts.catalogfilm.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private int id;

    @NotEmpty(message = "This field is required and cannot be empty.")
    @Size(min = 3, max = 18, message = "The username length must be between 3 and 18 characters.")
    private String username;

    @NotEmpty(message = "This field is required and cannot be empty.")
    @Email(message = "Email should be valid")
    private String email;

    private String password;
    private Set<RoleDto> roles = new HashSet<>();
}
