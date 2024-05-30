package odda.technologies.etudiantEvaluation.mappers;

import odda.technologies.etudiantEvaluation.dto.AnneeScolaireAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.AnneeScolaireDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnneeScolaireMapper {
    public static AnneeScolaireDTO convertAnneeScolaireToAnneeScolaireDTO(AnneeScolaire anneeScolaire) {
        return AnneeScolaireDTO.builder()
                .idAnneScolaire(anneeScolaire.getIdAnneScolaire())
                .libelle(anneeScolaire.getLibelle())
                .etat(anneeScolaire.isEtat())
                .build();
    }
    public static AnneeScolaireAvecListeInscriptionsDTO convertAnScolaireToAnneeScolaireAvecListInscDTO(AnneeScolaire anneeScolaire) {
        List<InscriptionDTO> listInscriptions = anneeScolaire.getListInscriptions() != null
                ? anneeScolaire.getListInscriptions().stream()
                .map(inscription -> InscriptionDTO.builder()
                        .idInscription(inscription.getIdInscription())
                        .date(inscription.getDate())
                        .statut(inscription.getStatut())
                        .etudiantId(inscription.getEtudiant().getIdEtudiant())
                        .anneeScolaireId(inscription.getAnneeScolaire().getIdAnneScolaire())
                        .filiereId(inscription.getFiliere().getIdFiliere())
                        .build())
                .collect(Collectors.toList())
                : new ArrayList<>();

        return AnneeScolaireAvecListeInscriptionsDTO.builder()
                .idAnneScolaire(anneeScolaire.getIdAnneScolaire())
                .libelle(anneeScolaire.getLibelle())
                .etat(anneeScolaire.isEtat())
                .listInscriptions(listInscriptions)

                .build();
    }

    public static AnneeScolaire convertAnneeScolaireDTOToAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) {
        return AnneeScolaire.builder()
                .idAnneScolaire(anneeScolaireDTO.getIdAnneScolaire())
                .libelle(anneeScolaireDTO.getLibelle())
                .etat(anneeScolaireDTO.isEtat())
                .build();
    }
}
