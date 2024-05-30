package odda.technologies.etudiantEvaluation.serviceImplementations;

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
    private EtudiantRepository etudiantRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private AnneeScolaireService anneeScolaireService;


    @Override
    public InscriptionDTO inscrireEtudiant(InscriptionAvecParentsInfosDTO inscriptionDTO) {
        Etudiant etudiant = etudiantRepository.findById(inscriptionDTO.getEtudiantDTO().getIdEtudiant()).orElseThrow();
        Filiere filiere = filiereRepository.findById(inscriptionDTO.getFiliereDTO().getIdFiliere()).orElseThrow();
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
                .orElseThrow(() -> new IllegalArgumentException("Inscription non trouvée"));
        inscription.setStatut(StatutInscriptionEnum.VALIDE);
        Inscription inscriptionSaved = inscriptionRepository.save(inscription);
        return InscriptionMapper.convertInscriptionToInscriptionDTO(inscriptionSaved);
    }

    @Override
    public InscriptionDTO invaliderInscription(long idInscription) {
        Inscription inscription = inscriptionRepository.findById(idInscription)
                .orElseThrow(() -> new IllegalArgumentException("Inscription non trouvée"));
        inscription.setStatut(StatutInscriptionEnum.NON_VALIDE);
        Inscription inscriptionSaved = inscriptionRepository.save(inscription);
        return InscriptionMapper.convertInscriptionToInscriptionDTO(inscriptionSaved);
    }

    @Override
    public List<InscriptionAvecParentsInfosDTO> listInscriptions(){
        List<Inscription> inscriptions=inscriptionRepository.findAll();
        List<InscriptionAvecParentsInfosDTO> inscriptionDTOS=new ArrayList<>();
        for(Inscription inscription:inscriptions){
            InscriptionAvecParentsInfosDTO inscriptionDTO=InscriptionMapper.convertInscriptionToInscriptionAvecParentsDTO(inscription);
            inscriptionDTOS.add(inscriptionDTO);
        }
        return inscriptionDTOS;
    }
    @Override
    public List<InscriptionDTO> listInscriptionsByEtudiant(long idEtudiant){
        List <Inscription> inscriptions=inscriptionRepository.findByEtudiantIdEtudiant(idEtudiant);
        List<InscriptionDTO> inscriptionDTOS=new ArrayList<>();
        for(Inscription inscription:inscriptions){
            InscriptionDTO inscriptionDTO=InscriptionMapper.convertInscriptionToInscriptionDTO(inscription);
            inscriptionDTOS.add(inscriptionDTO);
        }
        return inscriptionDTOS;
    }
    @Override
    public List<InscriptionDTO> ListerInscriptionsValideByEtudiant(long idEtudiant){
        List<Inscription> validInscriptions = inscriptionRepository.findByEtudiantIdEtudiantAndStatut(idEtudiant, StatutInscriptionEnum.VALIDE);
        return validInscriptions.stream()
                .map(InscriptionMapper::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<InscriptionDTO> ListerInscriptionsNonValideByEtudiant(long idEtudiant){
        List<Inscription> nonValidInscriptions = inscriptionRepository.findByEtudiantIdEtudiantAndStatut(idEtudiant, StatutInscriptionEnum.NON_VALIDE);
        return nonValidInscriptions.stream()
                .map(InscriptionMapper::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }


}
