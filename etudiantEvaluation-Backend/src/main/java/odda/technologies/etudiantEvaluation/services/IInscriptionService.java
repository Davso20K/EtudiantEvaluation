package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;

import java.util.List;

public interface IInscriptionService {
    public InscriptionDTO inscrireEtudiant(long etudiantId, long filiereId, StatutInscriptionEnum statut);
    public InscriptionDTO validerInscription(long idInscription);
    public InscriptionDTO invaliderInscription(long idInscription);
    public List<InscriptionDTO> listInscriptions();
    public List<InscriptionDTO> listInscriptionsByEtudiant(long id);
    public List<InscriptionDTO> ListerInscriptionsValideByEtudiant(long id);
    public List<InscriptionDTO> ListerInscriptionsNonValideByEtudiant(long id);



}
