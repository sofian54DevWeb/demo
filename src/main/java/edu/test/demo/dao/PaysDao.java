package edu.test.demo.dao;

import edu.test.demo.model.Pays;
import edu.test.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysDao extends JpaRepository<Pays, Integer> {

}
