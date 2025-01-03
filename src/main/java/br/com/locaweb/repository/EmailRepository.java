package br.com.locaweb.repository;

import br.com.locaweb.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> findByTitle(String title);

    List<Email> findByUserUserId(Long userId);

    Optional<Email> findById(Long id);

    List<Email> findByDestination(String email);

    List<Email> findByDestinationAndStatus(String destination, String status);

}
