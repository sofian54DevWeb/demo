package edu.test.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.test.demo.view.VueCompetence;
import edu.test.demo.view.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Utilisateur {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private Integer id;
    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private String nom;
    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private String prenom;
    @ManyToOne(optional = false)
    @JsonView(VueUtilisateur.class)
    private Pays pays;

    @ManyToMany
    @JoinTable(name="competence_utilisateur",
            joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns =@JoinColumn(name="competence_id"))
    @JsonView(VueUtilisateur.class)
    private Set<Competence>listeCompetence = new HashSet<>();
}
