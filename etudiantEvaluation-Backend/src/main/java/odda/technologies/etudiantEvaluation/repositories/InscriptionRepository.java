package odda.technologies.etudiantEvaluation.repositories;

import odda.technologies.etudiantEvaluation.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
}
