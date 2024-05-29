package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.services.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {
    @Autowired
    private IInscriptionService inscriptionService;



    @GetMapping("")
    public List<InscriptionDTO> listerInscriptions() {
        return inscriptionService.listInscriptions();
    }

    @GetMapping("/etudiant/{idEtudiant}")
    public List<InscriptionDTO> listerInscriptionsParEtudiant(@PathVariable Long idEtudiant){
        return inscriptionService.listInscriptionsByEtudiant(idEtudiant);
    }
    @GetMapping("/valides/etudiant/{idEtudiant}")
    public List<InscriptionDTO> listerInscriptionsValideParEtudiant(@PathVariable Long idEtudiant){
        return inscriptionService.ListerInscriptionsValideByEtudiant(idEtudiant);
    }
    @GetMapping("/non_valides/etudiant/{idEtudiant}")
    public List<InscriptionDTO> listerInscriptionsNonValideParEtudiant(@PathVariable Long idEtudiant){
        return inscriptionService.ListerInscriptionsNonValideByEtudiant(idEtudiant);
    }

    @PostMapping("")
    public InscriptionDTO inscrireEtudiant(@RequestBody InscriptionDTO inscriptionDTO) {
        return inscriptionService.inscrireEtudiant(inscriptionDTO.getEtudiantDTO().getIdEtudiant(),
                inscriptionDTO.getFiliereDTO().getIdFiliere(), inscriptionDTO.getStatut());
    }
    @PostMapping("valider/{idInscription}")
    public InscriptionDTO validerUneInscription(@PathVariable long idInscription) {
        return inscriptionService.validerInscription(idInscription);
    }
    @PostMapping("invalider/{idInscription}")
    public InscriptionDTO invaliderUneInscription(@PathVariable long idInscription) {
        return inscriptionService.invaliderInscription(idInscription);
    }
   /* @GetMapping("/{id}")
    public InscriptionDTO obtenirInscription(@PathVariable Long id) {
        return inscriptionService.obtenirInscription(id);
    }

    @PutMapping("/{id}")
    public InscriptionDTO mettreAJourInscription(@PathVariable Long id, @RequestBody InscriptionDTO inscriptionDTO) {
        return inscriptionService.mettreAJourInscription(id, inscriptionDTO);
    }

    @DeleteMapping("/{id}")
    public void supprimerInscription(@PathVariable Long id) {
        inscriptionService.supprimerInscription(id);
    }*/
}

