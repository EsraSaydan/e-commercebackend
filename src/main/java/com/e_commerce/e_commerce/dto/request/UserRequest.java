package com.e_commerce.e_commerce.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record UserRequest(@NotBlank String name, @Email String email, @NotBlank String password, @NotBlank String role) {

}