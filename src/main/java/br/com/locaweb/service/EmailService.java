package br.com.locaweb.service;

import br.com.locaweb.model.Email;
import br.com.locaweb.model.User;
import br.com.locaweb.model.UserSpam;
import br.com.locaweb.repository.EmailRepository;
import br.com.locaweb.repository.UserRepository;
import br.com.locaweb.repository.UserSpamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserSpamRepository spamRepository;

    public Email send(Email email, Long userId) {

        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(authenticatedUser.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User recipientUser = (User) userRepository.findByEmail(email.getDestination());

        List<UserSpam> spamList = spamRepository.findBySpamUserId(recipientUser.getUserId());

        boolean isSenderInSpamList = spamList.stream()
                .anyMatch(spam -> spam.getUserId().equals(user.getUserId()));

        if (isSenderInSpamList) {
            email.setStatus("spam");
        } else {
            email.setStatus("inbox");
        }


        email.setUser(user);
        return repository.save(email);
    }

    public void delete(String title) {
        Optional<Email> optionalEmail = repository.findByTitle(title);

        if(optionalEmail.isPresent()) {
            repository.delete(optionalEmail.get());
        } else {
            throw new RuntimeException("Email not found");
        }
    }

    public List<Email> getEmailsByDestination() {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserSpam> spamUsers = spamRepository.findSpamUserIdByUserId(authenticatedUser.getUserId());

        List<Long> spamUserIds = spamUsers.stream()
                .map(UserSpam::getSpamUserId)
                .collect(Collectors.toList());

        System.out.println("Spam User IDs (excluindo o próprio usuário): " + spamUserIds);

        List<Email> allEmails = repository.findByDestinationAndStatus(authenticatedUser.getEmail(), "inbox");

        return allEmails.stream()
                .filter(email -> !spamUserIds.contains(email.getUser().getUserId()))
                .collect(Collectors.toList());
    }

/*    public List<Email> getEmailsForAuthenticatedUser() {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Email> emails = repository.findByDestinationAndStatus(authenticatedUser.getEmail(), "inbox");

        return emails.stream()
                .filter(email -> !isSpammedByAuthenticatedUser(authenticatedUser, email.getUser()))
                .toList();
    }*/

    public List<Email> getEmailsByAuthenticatedUser() {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return repository.findByUserUserId(authenticatedUser.getUserId());
    }

    public Email getEmailById(Long id) {
        Optional<Email> emailOptional = repository.findById(id);

        return emailOptional.orElse(null);

    }

    public List<Email> getEmailByStatus() {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return repository.findByDestinationAndStatus(authenticatedUser.getEmail(), "trash");
    }

    public Email updateStatus(Long id) {
        Email email = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Email not found"));

        email.setStatus("trash");
        return repository.save(email);
    }

    public List<Email> getEmailsByStatusForSpam() {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return repository.findByDestinationAndStatus(authenticatedUser.getEmail(), "spam");
    }

    public Email updateSpam(Long id) {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Email email = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        email.setStatus("spam");
        Email emailSave =  repository.save(email);

        UserSpam userSpam = new UserSpam();
        userSpam.setUserId(authenticatedUser.getUserId());
        userSpam.setSpamUserId(email.getUser().getUserId());
        userSpam.setEmail(email.getUser().getEmail());
        spamRepository.save(userSpam);

        return emailSave;

    }


}
