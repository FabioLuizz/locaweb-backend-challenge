package br.com.locaweb.repository;

import br.com.locaweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);


}
