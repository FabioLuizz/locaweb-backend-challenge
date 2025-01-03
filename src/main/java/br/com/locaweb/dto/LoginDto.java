package br.com.locaweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(

        @NotBlank(message = "User e-mail is mandatory")
        @Email
        String email,

        @NotBlank(message = "User password is mandatory")
        @Size(min = 6, max = 20, message = "The password must contain between 6 and 20 characters")
        String password

) {
}
