package odda.technologies.etudiantEvaluation.repositories;

import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;
import odda.technologies.etudiantEvaluation.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    List<Inscription> findByEtudiantIdEtudiant(long idEtudiant);
    List<Inscription> findByEtudiantIdEtudiantAndStatut(long idEtudiant, StatutInscriptionEnum statut);
}
