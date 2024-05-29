package odda.technologies.etudiantEvaluation.services;

import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;

public interface IInscriptionService {
    public InscriptionDTO inscrireEtudiant(long etudiantId, long filiereId, String statut);

}
