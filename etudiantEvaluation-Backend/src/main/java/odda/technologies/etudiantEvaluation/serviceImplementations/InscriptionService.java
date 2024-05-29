package odda.technologies.etudiantEvaluation.serviceImplementations;

import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;
import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.entities.AnneeScolaire;
import odda.technologies.etudiantEvaluation.entities.Etudiant;
import odda.technologies.etudiantEvaluation.entities.Filiere;
import odda.technologies.etudiantEvaluation.entities.Inscription;
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
    public InscriptionDTO inscrireEtudiant(long etudiantId, long filiereId, StatutInscriptionEnum statut) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow();
        Filiere filiere = filiereRepository.findById(filiereId).orElseThrow();
        AnneeScolaire anneeScolaire = anneeScolaireService.obtenirAnneeScolaireActuelle();

        Inscription inscription = new Inscription();
        inscription.setEtudiant(etudiant);
        inscription.setFiliere(filiere);
        inscription.setAnneeScolaire(anneeScolaire);
        inscription.setDate(new Date()); // Utilisation de la date actuelle
        inscription.setStatut(statut);
        inscription = inscriptionRepository.save(inscription);

        return convertInscriptionToInscriptionDTO(inscription);
    }
    @Override
    public List<InscriptionDTO> listInscriptions(){
        List<Inscription> inscriptions=inscriptionRepository.findAll();
        List<InscriptionDTO> inscriptionDTOS=new ArrayList<>();
        for(Inscription inscription:inscriptions){
            InscriptionDTO inscriptionDTO=convertInscriptionToInscriptionDTO(inscription);
            inscriptionDTOS.add(inscriptionDTO);
        }
        return inscriptionDTOS;
    }
    @Override
    public List<InscriptionDTO> listInscriptionsByEtudiant(long idEtudiant){
        List <Inscription> inscriptions=inscriptionRepository.findByEtudiantIdEtudiant(idEtudiant);
        List<InscriptionDTO> inscriptionDTOS=new ArrayList<>();
        for(Inscription inscription:inscriptions){
            InscriptionDTO inscriptionDTO=convertInscriptionToInscriptionDTO(inscription);
            inscriptionDTOS.add(inscriptionDTO);
        }
        return inscriptionDTOS;
    }
    @Override
    public List<InscriptionDTO> ListerInscriptionsValideByEtudiant(long idEtudiant){
        List<Inscription> validInscriptions = inscriptionRepository.findByEtudiantIdEtudiantAndStatut(idEtudiant, StatutInscriptionEnum.VALIDE);
        return validInscriptions.stream()
                .map(InscriptionService::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<InscriptionDTO> ListerInscriptionsNonValideByEtudiant(long idEtudiant){
        List<Inscription> nonValidInscriptions = inscriptionRepository.findByEtudiantIdEtudiantAndStatut(idEtudiant, StatutInscriptionEnum.NON_VALIDE);
        return nonValidInscriptions.stream()
                .map(InscriptionService::convertInscriptionToInscriptionDTO)
                .collect(Collectors.toList());
    }

    public static InscriptionDTO convertInscriptionToInscriptionDTO(Inscription inscription) {
        return InscriptionDTO.builder()
                .idInscription(inscription.getIdInscription())
                .date(inscription.getDate())
                .statut(inscription.getStatut())
                .etudiantDTO(EtudiantService.convertEtudiantToEtudiantDTO(inscription.getEtudiant()))
                .filiereDTO(FiliereService.convertFiliereToFiliereDTO(inscription.getFiliere()))
                .anneeScolaireDTO(AnneeScolaireService.convertAnneeScolaireToAnneeScolaireDTO(inscription.getAnneeScolaire()))
                .build();
    }

    public static  Inscription convertInscriptionDTOToInscription(InscriptionDTO inscriptionDTO, Etudiant etudiant, Filiere filiere, AnneeScolaire anneeScolaire) {
        Inscription inscription = new Inscription();
        inscription.setDate(new Date());
        inscription.setStatut(inscriptionDTO.getStatut());
        inscription.setEtudiant(etudiant);
        inscription.setFiliere(filiere);
        inscription.setAnneeScolaire(anneeScolaire);
        return inscription;
    }
}
