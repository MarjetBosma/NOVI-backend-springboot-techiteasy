package nl.novi.techiteasy.dtos.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.novi.techiteasy.models.Authority;

import java.util.Set;

public class UserResponseDto {

    public String username;
    public String email;

    @JsonSerialize
    public Set<Authority> authorities;

    public UserResponseDto(String username, String email, Set<Authority> authorities) {
        this.username = username;
        this.email = email;
        this.authorities = authorities;
    }
}



