package br.com.locaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_SPAM_USERS")
@NoArgsConstructor
@AllArgsConstructor
public class UserSpam {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SPAM_USERS")
    @SequenceGenerator(name = "SQ_SPAM_USERS", sequenceName = "SQ_SPAM_USERS", allocationSize = 1)

    @Column(name = "ID_SPAM")
    private Long id;

    @Column(name = "ID_USER")
    private Long userId;

    @Column(name = "ID_SPAM_USER")
    private Long spamUserId;

    @Column(name = "DS_SPAM_USER_EMAIL")
    private String email;

}
