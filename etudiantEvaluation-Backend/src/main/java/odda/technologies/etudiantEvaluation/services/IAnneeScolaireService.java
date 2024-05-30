package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.dto.AnneeScolaireAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.AnneeScolaireDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;

import java.util.List;

public interface IAnneeScolaireService {

    public AnneeScolaire obtenirAnneeScolaireActuelle();
    public AnneeScolaireAvecListeInscriptionsDTO obtenirAnneeScolaire(Long id);
    public AnneeScolaire obtenirAnneeScolaireSanslisteInscriptions(Long idFiliere);

    public List<AnneeScolaireAvecListeInscriptionsDTO> listerAnneesScolaires();

    public void supprimerAnneeScolaire(Long id);
}
