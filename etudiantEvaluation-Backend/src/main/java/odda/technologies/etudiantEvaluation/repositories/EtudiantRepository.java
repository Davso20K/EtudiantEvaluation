package odda.technologies.etudiantEvaluation.repositories;

import odda.technologies.etudiantEvaluation.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
