package odda.technologies.etudiantEvaluation.serviceImplementations;

import jakarta.persistence.EntityNotFoundException;
import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;
import odda.technologies.etudiantEvaluation.dto.InscriptionAvecParentsInfosDTO;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;
import odda.technologies.etudiantEvaluation.entities.Etudiant;
import odda.technologies.etudiantEvaluation.entities.Filiere;
import odda.technologies.etudiantEvaluation.entities.Inscription;
import odda.technologies.etudiantEvaluation.mappers.AnneeScolaireMapper;
import odda.technologies.etudiantEvaluation.mappers.EtudiantMapper;
import odda.technologies.etudiantEvaluation.mappers.FiliereMapper;
import odda.technologies.etudiantEvaluation.mappers.InscriptionMapper;
import odda.technologies.etudiantEvaluation.repositories.EtudiantRepository;
import odda.technologies.etudiantEvaluation.repositories.FiliereRepository;
import odda.technologies.etudiantEvaluation.repositories.InscriptionRepository;
import odda.technologies.etudiantEvaluation.services.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InscriptionService implements IInscriptionService {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private FiliereService filiereService;
    @Autowired
    private AnneeScolaireService anneeScolaireService;


    @Override
    public InscriptionDTO inscrireEtudiant(InscriptionAvecParentsInfosDTO inscriptionDTO) {
        Etudiant etudiant = etudiantService.obtenirEtudiantSanslisteInscriptions(inscriptionDTO.getEtudiantDTO().getIdEtudiant());
        Filiere filiere = filiereService.obtenirFiliereSanslisteInscriptions(inscriptionDTO.getFiliereDTO().getIdFiliere());
        AnneeScolaire anneeScolaire = anneeScolaireService.obtenirAnneeScolaireActuelle();
        StatutInscriptionEnum statut=inscriptionDTO.getStatut();
        Inscription inscription = new Inscription();

        inscription.setEtudiant(etudiant);
        inscription.setFiliere(filiere);
        inscription.setAnneeScolaire(anneeScolaire);
        inscription.setDate(new Date()); // Utilisation de la date actuelle
        inscription.setStatut(statut);
        inscription = inscriptionRepository.save(inscription);

        return InscriptionMapper.convertInscriptionToInscriptionDTO(inscription);
    }
    @Override
    public InscriptionDTO validerInscription(long idInscription) {
        Inscription inscription = inscriptionRepository.findById(idInscription)
                .orElseThrow(() -> new EntityNotFoundException("Inscription non trouvée"));
        inscription.setStatut(StatutInscriptionEnum.VALIDE);
        Inscription inscriptionSaved = inscriptionRepository.save(inscription);
        return InscriptionMapper.convertInscriptionToInscriptionDTO(inscriptionSaved);
    }

    @Override
    public InscriptionDTO invaliderInscription(long idInscription) {
        Inscription inscription = inscriptionRepository.findById(idInscription)
                .orElseThrow(() -> new EntityNotFoundException("Inscription non trouvée"));
        inscription.setStatut(StatutInscriptionEnum.NON_VALIDE);
        Inscription inscriptionSaved = inscriptionRepository.save(inscription);
        return InscriptionMapper.convertInscriptionToInscriptionDTO(inscriptionSaved);
    }

    @Override
    public List<InscriptionAvecParentsInfosDTO> listInscriptions() {
        try {
            return inscriptionRepository.findAll().stream()
                    .map(InscriptionMapper::convertInscriptionToInscriptionAvecParentsDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur inattendue lors de la récupération de la liste des inscriptions.", e);
        }
    }


    @Override
    public List<InscriptionDTO> listInscriptionsByEtudiant(long idEtudiant) {
        List<Inscription> inscriptions = inscriptionRepository.findByEtudiantIdEtudiant(idEtudiant);

        if (inscriptions.isEmpty()) {
            throw new EntityNotFoundException("Aucune inscription trouvée pour l'étudiant ");
        }

        return inscriptions.stream()
                .map(InscriptionMapper::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<InscriptionDTO> ListerInscriptionsValideByEtudiant(long idEtudiant) {
        List<Inscription> inscriptions = inscriptionRepository.findByEtudiantIdEtudiantAndStatut(idEtudiant, StatutInscriptionEnum.VALIDE);

        if (inscriptions.isEmpty()) {
            throw new EntityNotFoundException("Aucune inscription valide trouvée pour l'étudiant  " );
        }

        return inscriptions.stream()
                .map(InscriptionMapper::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InscriptionDTO> ListerInscriptionsNonValideByEtudiant(long idEtudiant) {
        List<Inscription> inscriptions = inscriptionRepository.findByEtudiantIdEtudiantAndStatut(idEtudiant, StatutInscriptionEnum.NON_VALIDE);

        if (inscriptions.isEmpty()) {
            throw new EntityNotFoundException("Aucune inscription non valide trouvée pour l'étudiant " );
        }

        return inscriptions.stream()
                .map(InscriptionMapper::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }



}
