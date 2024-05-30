package odda.technologies.etudiantEvaluation.serviceImplementations;

import jakarta.persistence.EntityNotFoundException;
import odda.technologies.etudiantEvaluation.dto.EtudiantAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.EtudiantDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.Etudiant;
import odda.technologies.etudiantEvaluation.entities.Inscription;
import odda.technologies.etudiantEvaluation.mappers.EtudiantMapper;
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
        Etudiant etudiant = EtudiantMapper.convertEtudiantDTOToEtudiant(etudiantDTO);
        etudiant = etudiantRepository.save(etudiant);
        return EtudiantMapper.convertEtudiantToEtudiantDTO(etudiant);
    }

    @Override
    public EtudiantAvecListeInscriptionsDTO obtenirEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("L'étudiant n'a pas été trouvé."));
        return EtudiantMapper.convertEtudiantToEtudiantAvecListeInscDTO(etudiant) ;
    }
    @Override
    public Etudiant obtenirEtudiantSanslisteInscriptions(Long id) {
            return etudiantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("L'étudiant n'a pas été trouvé."));
    }

    @Override
    public List<EtudiantAvecListeInscriptionsDTO> listerEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<EtudiantAvecListeInscriptionsDTO> etudiantsDTO = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            EtudiantAvecListeInscriptionsDTO etudiantDTO = EtudiantMapper.convertEtudiantToEtudiantAvecListeInscDTO(etudiant);
            etudiantsDTO.add(etudiantDTO);
        }

        return etudiantsDTO;
    }

    @Override
    public EtudiantDTO mettreAJourEtudiant(Long id, EtudiantDTO etudiantDTO) {
        Etudiant etudiantExistant = etudiantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("L'étudiant n'a pas été trouvé."));

        Etudiant etudiant = EtudiantMapper.convertEtudiantDTOToEtudiant(etudiantDTO);
        etudiant.setIdEtudiant(id);
        etudiant = etudiantRepository.save(etudiant);

        return EtudiantMapper.convertEtudiantToEtudiantDTO(etudiant);
    }


    @Override
    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }


}
