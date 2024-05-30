package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.EtudiantAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.EtudiantDTO;
import odda.technologies.etudiantEvaluation.serviceImplementations.EtudiantService;
import odda.technologies.etudiantEvaluation.services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    private IEtudiantService etudiantService;

    @PostMapping("")
    public EtudiantDTO creerEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.creerEtudiant(etudiantDTO);
    }
    @GetMapping("/{id}")
    public EtudiantAvecListeInscriptionsDTO obtenirEtudiant(@PathVariable Long id) {
         return etudiantService.obtenirEtudiant(id);

    }

    @GetMapping("")
    public List<EtudiantAvecListeInscriptionsDTO> listerEtudiants() {
        return etudiantService.listerEtudiants();
    }



    @PutMapping("/{id}")
    public EtudiantDTO mettreAJourEtudiant(@PathVariable Long id, @RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.mettreAJourEtudiant(id, etudiantDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void supprimerEtudiant(@PathVariable Long id) {
        etudiantService.supprimerEtudiant(id);
    }
}
