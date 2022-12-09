package edu.test.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.test.demo.dao.UtilisateurDao;
import edu.test.demo.model.Utilisateur;
import edu.test.demo.view.VueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @GetMapping("/utilisateur/{id}")
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable int id){
        Optional<Utilisateur> utilisateurExistant = utilisateurDao.findById(id);
        if(utilisateurExistant.isPresent()) {
            return new ResponseEntity<>(utilisateurExistant.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Pour afficher la base de donnée
    @GetMapping("/utilisateurs")
    @JsonView(VueUtilisateur.class)
    public List<Utilisateur> getUtilisateurs(){
        return utilisateurDao.findAll();
    }
    //Pour ajouter et modifier dans la base de donnée
    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> ajoutUtilisateur(@RequestBody Utilisateur utilisateur){

        //si l'utilisateur à un identifiant
        if (utilisateur.getId() != null){
            Optional<Utilisateur> utilisateurExistant =
                    utilisateurDao.findById(utilisateur.getId());

            //l'utilisateur à fournit un id existant dans la bdd(c'est un update)
            if(utilisateurExistant.isPresent()){
                utilisateurDao.save(utilisateur);
                return new ResponseEntity<>(utilisateur,HttpStatus.OK);

            }else{
                //l'utilisateur à fournit un id qui n'existe pas dans la bdd(c'est une erreur)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else {
            utilisateurDao.save(utilisateur);
            return new ResponseEntity<>(utilisateur,HttpStatus.CREATED);
        }
    }


    //Pour supprimer dans la base de donnée
    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> supprimeUtilisateur(@PathVariable int id) {
        Optional<Utilisateur> utilisateurExistant = utilisateurDao.findById(id);
        if (utilisateurExistant.isPresent()) {
            utilisateurDao.deleteById(id);
            return new ResponseEntity<>(utilisateurExistant.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}

