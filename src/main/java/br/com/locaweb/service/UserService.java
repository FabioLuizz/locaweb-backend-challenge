package br.com.locaweb.service;


import br.com.locaweb.dto.UserDisplayDTO;
import br.com.locaweb.dto.UserRegisterDTO;
import br.com.locaweb.exception.ExceptionHandler;
import br.com.locaweb.model.User;
import br.com.locaweb.model.UserRole;
import br.com.locaweb.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDisplayDTO register(UserRegisterDTO userRegisterDTO) {
        String EncryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.password());

        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(EncryptedPassword);

        if (userRegisterDTO.role() == null) {
            user.setRole(UserRole.USER); // Definir valor padrão
        } else {
            user.setRole(userRegisterDTO.role()); // Definir role a partir do DTO
        }

        User registeredUser = repository.save(user);

        return new UserDisplayDTO(registeredUser);
    }

    public void delete(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);

        if(optionalUser.isPresent()) {
            repository.delete(optionalUser.get());
        } else {
            throw new ExceptionHandler("User not found");
        }
    }

    public User update(User user) {
        Optional<User> optionalUser = repository.findById(user.getUserId());

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return repository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
