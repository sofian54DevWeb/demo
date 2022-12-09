package edu.test.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.test.demo.dao.CompetenceDao;
import edu.test.demo.model.Competence;
import edu.test.demo.view.VueCompetence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CompetenceController {

    @Autowired
    private CompetenceDao competenceDao;

    @GetMapping("/competence/{id}")

    public ResponseEntity<Competence> getCompetence(@PathVariable int id){
        Optional<Competence> competenceExistant = competenceDao.findById(id);
        if(competenceExistant.isPresent()) {
            return new ResponseEntity<>(competenceExistant.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Pour afficher la base de donnée
    @GetMapping("/competences")
    @JsonView(VueCompetence.class)
    public List<Competence> getCompetences(){
        return competenceDao.findAll();
    }
    //Pour ajouter et modifier dans la base de donnée
    @PostMapping("/competence")
    public ResponseEntity<Competence> ajoutCompetence(@RequestBody Competence competence){

        //si l'competence à un identifiant
        if (competence.getId() != null){
            Optional<Competence> competenceExistant =
                    competenceDao.findById(competence.getId());

            //l'competence à fournit un id existant dans la bdd(c'est un update)
            if(competenceExistant.isPresent()){
                competenceDao.save(competence);
                return new ResponseEntity<>(competence,HttpStatus.OK);

            }else{
                //l'competence à fournit un id qui n'existe pas dans la bdd(c'est une erreur)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else {
            competenceDao.save(competence);
            return new ResponseEntity<>(competence,HttpStatus.CREATED);
        }
    }


    //Pour supprimer dans la base de donnée
    @DeleteMapping("/competence/{id}")
    public ResponseEntity<Competence> supprimeCompetence(@PathVariable int id) {
        Optional<Competence> competenceExistant = competenceDao.findById(id);
        if (competenceExistant.isPresent()) {
            competenceDao.deleteById(id);
            return new ResponseEntity<>(competenceExistant.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}

