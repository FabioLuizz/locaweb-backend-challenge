package br.com.locaweb.repository;

import br.com.locaweb.model.User;
import br.com.locaweb.model.UserSpam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserSpamRepository extends JpaRepository<UserSpam, Long> {

    List<UserSpam> findSpamUserIdByUserId(Long userId);

    List<UserSpam> findBySpamUserId(Long spamUserId);

}