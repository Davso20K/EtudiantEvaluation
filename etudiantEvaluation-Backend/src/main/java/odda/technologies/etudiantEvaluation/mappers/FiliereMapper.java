package odda.technologies.etudiantEvaluation.mappers;

import odda.technologies.etudiantEvaluation.dto.FiliereAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.FiliereDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.Filiere;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiliereMapper {
    public static FiliereDTO convertFiliereToFiliereDTO(Filiere filiere) {


        return FiliereDTO.builder()
                .idFiliere(filiere.getIdFiliere())
                .code(filiere.getCode())
                .libelle(filiere.getLibelle())

                .build();
    }
    public static FiliereAvecListeInscriptionsDTO convertFiliereToFiliereAvecListeInscDTO(Filiere filiere) {
        List<InscriptionDTO> listInscriptions = filiere.getListInscriptions() != null
                ? filiere.getListInscriptions().stream()
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

        return FiliereAvecListeInscriptionsDTO.builder()
                .idFiliere(filiere.getIdFiliere())
                .code(filiere.getCode())
                .libelle(filiere.getLibelle())
                .listInscriptions(listInscriptions)
                .build();
    }

    public static Filiere convertFiliereDTOToFiliere(FiliereDTO filiereDTO) {
        return Filiere.builder()
                .idFiliere(filiereDTO.getIdFiliere())
                .code(filiereDTO.getCode())
                .libelle(filiereDTO.getLibelle())
                .build();
    }
}

