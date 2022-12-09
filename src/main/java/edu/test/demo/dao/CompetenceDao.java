package edu.test.demo.dao;

import edu.test.demo.model.Competence;
import edu.test.demo.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceDao extends JpaRepository<Competence, Integer> {

}
