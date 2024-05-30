package odda.technologies.etudiantEvaluation.mappers;

import odda.technologies.etudiantEvaluation.dto.EtudiantDTO;
import odda.technologies.etudiantEvaluation.dto.EtudiantAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.Etudiant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EtudiantMapper {

    public static EtudiantDTO convertEtudiantToEtudiantDTO(Etudiant etudiant) {


        return EtudiantDTO.builder()
                .idEtudiant(etudiant.getIdEtudiant())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .sexe(etudiant.getSexe())
                .dateNaissance(etudiant.getDateNaissance())
                .contact(etudiant.getContact())
                .email(etudiant.getEmail())
                .build();
    }

    public static EtudiantAvecListeInscriptionsDTO convertEtudiantToEtudiantAvecListeInscDTO(Etudiant etudiant) {
        List<InscriptionDTO> listInscriptions = etudiant.getListInscriptions() != null
                ? etudiant.getListInscriptions().stream()
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
        return EtudiantAvecListeInscriptionsDTO.builder()
                .idEtudiant(etudiant.getIdEtudiant())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .sexe(etudiant.getSexe())
                .dateNaissance(etudiant.getDateNaissance())
                .contact(etudiant.getContact())
                .email(etudiant.getEmail())
                .listInscriptions(listInscriptions)
                .build();
    }

    public static Etudiant convertEtudiantDTOToEtudiant(EtudiantDTO etudiantDTO) {
        return Etudiant.builder()
                .idEtudiant(etudiantDTO.getIdEtudiant())
                .nom(etudiantDTO.getNom())
                .prenom(etudiantDTO.getPrenom())
                .sexe(etudiantDTO.getSexe())
                .dateNaissance(etudiantDTO.getDateNaissance())
                .contact(etudiantDTO.getContact())
                .email(etudiantDTO.getEmail())
                .build();
    }
}
