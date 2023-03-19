package ru.mts.catalogfilm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "This field is required and cannot be empty.")
    @Size(min = 3, max = 18, message = "The username length must be between 3 and 18 characters.")
    private String username;

    @NotEmpty(message = "This field is required and cannot be empty.")
    @Email(message = "Email should be valid")
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    public void addRole(RoleEntity role) {
        this.roles.add(role);
    }
}