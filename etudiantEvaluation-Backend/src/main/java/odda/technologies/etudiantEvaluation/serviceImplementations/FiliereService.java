package odda.technologies.etudiantEvaluation.serviceImplementations;

import odda.technologies.etudiantEvaluation.dto.FiliereDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.Filiere;
import odda.technologies.etudiantEvaluation.repositories.FiliereRepository;
import odda.technologies.etudiantEvaluation.services.IFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiliereService implements IFiliereService {
    @Autowired
    private FiliereRepository filiereRepository;
    @Override
    public FiliereDTO creerFiliere(FiliereDTO filiereDTO) {
        Filiere filiere = convertFiliereDTOToFiliere(filiereDTO);
        filiere = filiereRepository.save(filiere);
        return convertFiliereToFiliereDTO(filiere);
    }

    @Override
    public FiliereDTO obtenirFiliere(Long id) {
        Filiere filiere = filiereRepository.findById(id).orElse(null);
        return filiere != null ? convertFiliereToFiliereDTO(filiere) : null;
    }

    @Override
    public List<FiliereDTO> listerFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream()
                .map(FiliereService::convertFiliereToFiliereDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FiliereDTO mettreAJourFiliere(Long id, FiliereDTO filiereDTO) {
        Filiere filiereExistant = filiereRepository.findById(id).orElse(null);
        if (filiereExistant != null) {
            Filiere filiere = convertFiliereDTOToFiliere(filiereDTO);
            filiere.setIdFiliere(id);
            filiere = filiereRepository.save(filiere);
            return convertFiliereToFiliereDTO(filiere);
        }
        return null;
    }

    @Override
    public void supprimerFiliere(Long id) {
        filiereRepository.deleteById(id);
    }

    public static FiliereDTO convertFiliereToFiliereDTO(Filiere filiere) {
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
                .toList()
                : new ArrayList<>();
        return FiliereDTO.builder()
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
