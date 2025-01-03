package br.com.locaweb.controller;

import br.com.locaweb.model.Email;
import br.com.locaweb.model.User;
import br.com.locaweb.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public Email send(@RequestBody @Valid Email email) {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getDetails();

        return service.send(email, userId);
    }

    @DeleteMapping("/{title}")
    public void delete(@PathVariable String title) {
        service.delete(title);
    }

    @GetMapping("/user")
    public List<Email> getEmailsByUser() {
        return service.getEmailsByAuthenticatedUser();
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<Email>> getSentEmails() {
        List<Email> emails = service.getEmailsByDestination();

        return ResponseEntity.ok(emails);
    }

    @GetMapping("/inbox/{id}")
    public ResponseEntity<Email> getById(@PathVariable Long id) {

        Email email = service.getEmailById(id);

        if(email != null) {
            return ResponseEntity.ok(email);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/trash")
    public List<Email> getEmailByStatus() {

        return service.getEmailByStatus();
    }

    @PutMapping("/inbox/{id}")
    public ResponseEntity<Email> UpdateStatus(@PathVariable Long id) {
        Email email = service.updateStatus(id);

        return ResponseEntity.ok(email);
    }

    @GetMapping("/spam")
    public List<Email> getEmailByStatusForSpam() {
        return service.getEmailsByStatusForSpam();
    }

    @PutMapping("/inbox/{id}/spam")
    public ResponseEntity<Email> UpdateSpam(@PathVariable Long id) {
        Email email = service.updateSpam(id);

        return ResponseEntity.ok(email);
    }


}
