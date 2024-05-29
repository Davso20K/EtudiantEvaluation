package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.dto.FiliereDTO;

import java.util.List;

public interface IFiliereService {
    public FiliereDTO creerFiliere(FiliereDTO filiereDTO);
    public FiliereDTO obtenirFiliere(Long id);
    public List<FiliereDTO> listerFilieres();
    public FiliereDTO mettreAJourFiliere(Long id, FiliereDTO filiereDTO);
    public void supprimerFiliere(Long id);
}
