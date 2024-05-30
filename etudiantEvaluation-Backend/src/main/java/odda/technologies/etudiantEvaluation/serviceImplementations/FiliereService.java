package odda.technologies.etudiantEvaluation.serviceImplementations;

import jakarta.persistence.EntityNotFoundException;
import odda.technologies.etudiantEvaluation.dto.FiliereAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.FiliereDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.Filiere;
import odda.technologies.etudiantEvaluation.mappers.FiliereMapper;
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
        Filiere filiere = FiliereMapper.convertFiliereDTOToFiliere(filiereDTO);
        filiere = filiereRepository.save(filiere);
        return FiliereMapper.convertFiliereToFiliereDTO(filiere);
    }

    @Override
    public FiliereAvecListeInscriptionsDTO obtenirFiliere(Long id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La filière  n'a pas été trouvée."));

        return FiliereMapper.convertFiliereToFiliereAvecListeInscDTO(filiere);
    }
    public Filiere obtenirFiliereSanslisteInscriptions(Long id){

        return filiereRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La filière  n'a pas été trouvée."));
    }

    @Override
    public List<FiliereAvecListeInscriptionsDTO> listerFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream()
                .map(FiliereMapper::convertFiliereToFiliereAvecListeInscDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FiliereDTO mettreAJourFiliere(Long id, FiliereDTO filiereDTO) {
        Filiere filiereExistante = filiereRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La filière n'a pas été trouvée."));

        Filiere filiere = FiliereMapper.convertFiliereDTOToFiliere(filiereDTO);
        filiere.setIdFiliere(id);
        filiere = filiereRepository.save(filiere);

        return FiliereMapper.convertFiliereToFiliereDTO(filiere);
    }

    @Override
    public void supprimerFiliere(Long id) {
        filiereRepository.deleteById(id);
    }


}
