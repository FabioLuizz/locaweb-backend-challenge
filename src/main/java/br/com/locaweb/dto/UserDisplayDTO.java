package br.com.locaweb.dto;

import br.com.locaweb.model.User;
import br.com.locaweb.model.UserRole;

public record UserDisplayDTO(

        Long userId,
        String name,
        String email,
        UserRole role

) {

    public UserDisplayDTO(User user) {
        this(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

}
