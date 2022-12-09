package edu.test.demo.controller;

import edu.test.demo.dao.PaysDao;
import edu.test.demo.model.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PaysController {

    @Autowired
    private PaysDao paysDao;

    @GetMapping("/pays/{id}")

    public ResponseEntity<Pays> getPays(@PathVariable int id){
        Optional<Pays> paysExistant = paysDao.findById(id);
        if(paysExistant.isPresent()) {
            return new ResponseEntity<>(paysExistant.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Pour afficher la base de donnée
    @GetMapping("/liste-pays")
    public List<Pays> getListePays(){
        return paysDao.findAll();
    }
    //Pour ajouter et modifier dans la base de donnée
    @PostMapping("/pays")
    public ResponseEntity<Pays> ajoutPays(@RequestBody Pays pays){

        //si l'pays à un identifiant
        if (pays.getId() != null){
            Optional<Pays> paysExistant =
                    paysDao.findById(pays.getId());

            //l'pays à fournit un id existant dans la bdd(c'est un update)
            if(paysExistant.isPresent()){
                paysDao.save(pays);
                return new ResponseEntity<>(pays,HttpStatus.OK);

            }else{
                //l'pays à fournit un id qui n'existe pas dans la bdd(c'est une erreur)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else {
            paysDao.save(pays);
            return new ResponseEntity<>(pays,HttpStatus.CREATED);
        }
    }


    //Pour supprimer dans la base de donnée
    @DeleteMapping("/pays/{id}")
    public ResponseEntity<Pays> supprimePays(@PathVariable int id) {
        Optional<Pays> paysExistant = paysDao.findById(id);
        if (paysExistant.isPresent()) {
            paysDao.deleteById(id);
            return new ResponseEntity<>(paysExistant.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}

