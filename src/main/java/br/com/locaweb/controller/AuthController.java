package br.com.locaweb.controller;

import br.com.locaweb.config.security.TokenService;
import br.com.locaweb.dto.LoginDto;
import br.com.locaweb.dto.TokenDTO;
import br.com.locaweb.dto.UserDisplayDTO;
import br.com.locaweb.dto.UserRegisterDTO;
import br.com.locaweb.model.User;
import br.com.locaweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locaweb")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDto loginDto) {

        UsernamePasswordAuthenticationToken usernamePassword =  new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserDisplayDTO register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        UserDisplayDTO userSave = null;
        userSave = service.register(userRegisterDTO);

        return userSave;
    }



}
