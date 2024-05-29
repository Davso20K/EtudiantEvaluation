package odda.technologies.etudiantEvaluation.serviceImplementations;

import odda.technologies.etudiantEvaluation.dto.EtudiantDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.Etudiant;
import odda.technologies.etudiantEvaluation.entities.Inscription;
import odda.technologies.etudiantEvaluation.repositories.EtudiantRepository;
import odda.technologies.etudiantEvaluation.services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantService implements IEtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Override
    public EtudiantDTO creerEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = convertEtudiantDTOToEtudiant(etudiantDTO);
        etudiant = etudiantRepository.save(etudiant);
        return convertEtudiantToEtudiantDTO(etudiant);
    }

    @Override
    public EtudiantDTO obtenirEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        return etudiant != null ? convertEtudiantToEtudiantDTO(etudiant) : null;
    }

    @Override
    public List<EtudiantDTO> listerEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<EtudiantDTO> etudiantsDTO = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            EtudiantDTO etudiantDTO = EtudiantService.convertEtudiantToEtudiantDTO(etudiant);
            etudiantsDTO.add(etudiantDTO);
        }

        return etudiantsDTO;
    }

    @Override
    public EtudiantDTO mettreAJourEtudiant(Long id, EtudiantDTO etudiantDTO) {
        Etudiant etudiantExistant = etudiantRepository.findById(id).orElse(null);
        if (etudiantExistant != null) {
            Etudiant etudiant =convertEtudiantDTOToEtudiant(etudiantDTO);
            etudiant.setIdEtudiant(id);
            etudiant = etudiantRepository.save(etudiant);
            return convertEtudiantToEtudiantDTO(etudiant);
        }
        return null;
    }

    @Override
    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }


    public static EtudiantDTO convertEtudiantToEtudiantDTO(Etudiant etudiant) {
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
                .toList()
                : new ArrayList<>();

        return EtudiantDTO.builder()
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
