package edu.test.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Competence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private Integer id;
    @JsonView(VueCompetence.class)
    private String nom;

    @ManyToMany(mappedBy = "listeCompetence")
    private Set<Utilisateur>listeUtilisateur = new HashSet<>();

}
