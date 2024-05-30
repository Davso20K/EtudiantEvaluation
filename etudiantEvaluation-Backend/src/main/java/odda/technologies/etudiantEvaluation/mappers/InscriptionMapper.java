package odda.technologies.etudiantEvaluation.mappers;

import odda.technologies.etudiantEvaluation.dto.InscriptionAvecParentsInfosDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;
import odda.technologies.etudiantEvaluation.entities.Etudiant;
import odda.technologies.etudiantEvaluation.entities.Filiere;
import odda.technologies.etudiantEvaluation.entities.Inscription;
import odda.technologies.etudiantEvaluation.repositories.EtudiantRepository;
import odda.technologies.etudiantEvaluation.repositories.FiliereRepository;
import odda.technologies.etudiantEvaluation.serviceImplementations.AnneeScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

public class InscriptionMapper {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private AnneeScolaireService anneeScolaireService;
    public static InscriptionDTO convertInscriptionToInscriptionDTO(Inscription inscription) {
        return InscriptionDTO.builder()
                .idInscription(inscription.getIdInscription())
                .date(inscription.getDate())
                .statut(inscription.getStatut())
                .etudiantId(inscription.getEtudiant().getIdEtudiant())
                .filiereId(inscription.getFiliere().getIdFiliere())
                .anneeScolaireId(inscription.getAnneeScolaire().getIdAnneScolaire())
                .build();
    }

    public static InscriptionAvecParentsInfosDTO convertInscriptionToInscriptionAvecParentsDTO(Inscription inscription) {
        return InscriptionAvecParentsInfosDTO.builder()
                .idInscription(inscription.getIdInscription())
                .date(inscription.getDate())
                .statut(inscription.getStatut())
                .etudiantId(inscription.getEtudiant().getIdEtudiant())
                .filiereId(inscription.getFiliere().getIdFiliere())
                .anneeScolaireId(inscription.getAnneeScolaire().getIdAnneScolaire())
                .etudiantDTO(EtudiantMapper.convertEtudiantToEtudiantDTO(inscription.getEtudiant()))
                .filiereDTO(FiliereMapper.convertFiliereToFiliereDTO(inscription.getFiliere()))
                .anneeScolaireDTO(AnneeScolaireMapper.convertAnneeScolaireToAnneeScolaireDTO(inscription.getAnneeScolaire()))
                .build();
    }

    public Inscription convertInscriptionDTOToInscription(InscriptionDTO inscriptionDTO) {
        Etudiant etudiant = etudiantRepository.findById(inscriptionDTO.getEtudiantId()).orElseThrow();
        Filiere filiere = filiereRepository.findById(inscriptionDTO.getFiliereId()).orElseThrow();
        AnneeScolaire anneeScolaire = anneeScolaireService.obtenirAnneeScolaireActuelle();

        Inscription inscription = new Inscription();
        inscription.setDate(new Date());
        inscription.setStatut(inscriptionDTO.getStatut());
        inscription.setEtudiant(etudiant);
        inscription.setFiliere(filiere);
        inscription.setAnneeScolaire(anneeScolaire);
        return inscription;
    }
}
