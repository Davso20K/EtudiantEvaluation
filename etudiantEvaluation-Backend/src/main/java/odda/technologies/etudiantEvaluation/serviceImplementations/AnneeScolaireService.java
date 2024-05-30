package odda.technologies.etudiantEvaluation.serviceImplementations;

import jakarta.persistence.EntityNotFoundException;
import odda.technologies.etudiantEvaluation.dto.AnneeScolaireAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.AnneeScolaireDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;
import odda.technologies.etudiantEvaluation.mappers.AnneeScolaireMapper;
import odda.technologies.etudiantEvaluation.repositories.AnneScolaireRepository;
import odda.technologies.etudiantEvaluation.services.IAnneeScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnneeScolaireService implements IAnneeScolaireService {
    @Autowired
    private AnneScolaireRepository anneeScolaireRepository;

    @Override
    public AnneeScolaire obtenirAnneeScolaireActuelle() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        if (month < Calendar.SEPTEMBER) {
            year--; // Si nous sommes avant septembre, l'année scolaire actuelle est l'année précédente
        }

        String libelleAnneeScolaire = year + "-" + (year + 1);

        Optional<AnneeScolaire> anneeScolaire = anneeScolaireRepository.findByLibelle(libelleAnneeScolaire);

        if (anneeScolaire.isEmpty()) {
            // Si l'année scolaire n'existe pas, on la crée
            AnneeScolaire nouvelleAnneeScolaire = new AnneeScolaire();
            nouvelleAnneeScolaire.setLibelle(libelleAnneeScolaire);
            nouvelleAnneeScolaire.setEtat(true);
            anneeScolaireRepository.save(nouvelleAnneeScolaire);
            return nouvelleAnneeScolaire;
        }

        return anneeScolaire.get();
    }


    @Override
    public AnneeScolaireAvecListeInscriptionsDTO obtenirAnneeScolaire(Long id) {
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("L'année scolaire n'a pas été trouvée."));

        return AnneeScolaireMapper.convertAnScolaireToAnneeScolaireAvecListInscDTO(anneeScolaire);
    }
    @Override
    public AnneeScolaire obtenirAnneeScolaireSanslisteInscriptions(Long id){

        return anneeScolaireRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("L'année scolaire n'a pas été trouvée."));
    }



    @Override
    public List<AnneeScolaireAvecListeInscriptionsDTO> listerAnneesScolaires() {
        List<AnneeScolaire> anneesScolaires = anneeScolaireRepository.findAll();
        return anneesScolaires.stream()
                .map(AnneeScolaireMapper::convertAnScolaireToAnneeScolaireAvecListInscDTO)
                .collect(Collectors.toList());
    }



    @Override
    public void supprimerAnneeScolaire(Long id) {
        anneeScolaireRepository.deleteById(id);
    }


}
