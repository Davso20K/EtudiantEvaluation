package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.dto.AnneeScolaireDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;

import java.util.List;

public interface IAnneeScolaireService {

    public AnneeScolaire obtenirAnneeScolaireActuelle();
    public AnneeScolaireDTO obtenirAnneeScolaire(Long id);
    public List<AnneeScolaireDTO> listerAnneesScolaires();

    public void supprimerAnneeScolaire(Long id);
}
