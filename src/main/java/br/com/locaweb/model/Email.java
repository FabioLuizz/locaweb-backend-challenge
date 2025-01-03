package br.com.locaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "TB_EMAILS")
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMAILS")
    @SequenceGenerator(name = "SQ_EMAILS", sequenceName = "SQ_EMAILS", allocationSize = 1)

    @Column(name = "ID_EMAIL")
    private Long id;

    @Column(name = "DS_DESTINATION", nullable = false)
    private String destination;

    @Column(name = "NM_TITLE", nullable = false)
    private String title;

    @Column(name = "DS_SUBJECT", nullable = false)
    private String subject;

    @Column(name = "DT_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "ST_STATUS", nullable = false)
    private String status = "inbox";

    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;

}