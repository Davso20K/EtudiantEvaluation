package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;
import odda.technologies.etudiantEvaluation.dto.InscriptionAvecParentsInfosDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;

import java.util.List;

public interface IInscriptionService {
    public InscriptionDTO inscrireEtudiant(InscriptionAvecParentsInfosDTO inscriptionDTO);
    public InscriptionDTO validerInscription(long idInscription);
    public InscriptionDTO invaliderInscription(long idInscription);
    public List<InscriptionAvecParentsInfosDTO> listInscriptions();
    public List<InscriptionDTO> listInscriptionsByEtudiant(long id);
    public List<InscriptionDTO> ListerInscriptionsValideByEtudiant(long id);
    public List<InscriptionDTO> ListerInscriptionsNonValideByEtudiant(long id);



}
