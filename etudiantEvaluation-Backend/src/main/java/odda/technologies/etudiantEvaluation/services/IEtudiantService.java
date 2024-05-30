package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.dto.EtudiantAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.EtudiantDTO;
import odda.technologies.etudiantEvaluation.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    public EtudiantDTO creerEtudiant(EtudiantDTO etudiantDTO);
    public EtudiantAvecListeInscriptionsDTO obtenirEtudiant(Long id);
    public List<EtudiantAvecListeInscriptionsDTO> listerEtudiants();
    public EtudiantDTO mettreAJourEtudiant(Long id, EtudiantDTO etudiantDTO);
    public void supprimerEtudiant(Long id);

}
