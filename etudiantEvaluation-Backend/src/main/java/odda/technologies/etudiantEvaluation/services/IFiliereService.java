package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.dto.FiliereAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.FiliereDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;
import odda.technologies.etudiantEvaluation.entities.Filiere;

import java.util.List;
import java.util.Optional;

public interface IFiliereService {
    public FiliereDTO creerFiliere(FiliereDTO filiereDTO);
    public FiliereAvecListeInscriptionsDTO obtenirFiliere(Long id);
    public Filiere obtenirFiliereSanslisteInscriptions(Long id);
    public List<FiliereAvecListeInscriptionsDTO> listerFilieres();
    public FiliereDTO mettreAJourFiliere(Long id, FiliereDTO filiereDTO);
    public void supprimerFiliere(Long id);

}
