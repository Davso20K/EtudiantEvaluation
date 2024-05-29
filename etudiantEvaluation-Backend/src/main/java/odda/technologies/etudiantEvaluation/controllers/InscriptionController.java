package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.InscriptionDTO;
import odda.technologies.etudiantEvaluation.services.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

