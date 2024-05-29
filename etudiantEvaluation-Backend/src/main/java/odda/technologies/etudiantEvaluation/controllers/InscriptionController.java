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

    @PostMapping("")
    public InscriptionDTO inscrireEtudiant(@RequestBody InscriptionDTO inscriptionDTO) {
        return inscriptionService.inscrireEtudiant(inscriptionDTO.getEtudiantDTO().getIdEtudiant(),
                inscriptionDTO.getFiliereDTO().getIdFiliere(), inscriptionDTO.getStatut());
    }

    @GetMapping("")
    public List<InscriptionDTO> listerInscriptions() {
        return inscriptionService.listInscriptions();
    }

    @GetMapping("/{idEtudiant}")
    public List<InscriptionDTO> listerInscriptionsParEtudiant(@PathVariable Long id){
        return inscriptionService.listInscriptionsByEtudiant(id);
    }
    @GetMapping("/valide/{idEtudiant}/")
    public List<InscriptionDTO> listerInscriptionsValideParEtudiant(@PathVariable Long id){
        return inscriptionService.ListerInscriptionsValideByEtudiant(id);
    }
    @GetMapping("/non_valide/{idEtudiant}/")
    public List<InscriptionDTO> listerInscriptionsNonValideParEtudiant(@PathVariable Long id){
        return inscriptionService.ListerInscriptionsNonValideByEtudiant(id);
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

