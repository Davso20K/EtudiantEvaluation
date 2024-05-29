package odda.technologies.etudiantEvaluation.repositories;

import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnneScolaireRepository extends JpaRepository<AnneeScolaire,Long> {
    Optional<AnneeScolaire> findByLibelle(String libelle);
}
