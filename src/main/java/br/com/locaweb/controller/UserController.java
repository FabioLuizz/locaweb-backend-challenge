package br.com.locaweb.controller;

import br.com.locaweb.dto.UserDisplayDTO;
import br.com.locaweb.dto.UserRegisterDTO;
import br.com.locaweb.model.User;
import br.com.locaweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/locaweb")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    /*@PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDisplayDTO register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return service.register(userRegisterDTO);
    }*/

    @DeleteMapping("/profile/{userId}")
    public void delete(@PathVariable Long userId) {
        service.delete(userId);
    }

    @PutMapping("/profile/update")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody @Valid User user) {
        return service.update(user);
    }

}
