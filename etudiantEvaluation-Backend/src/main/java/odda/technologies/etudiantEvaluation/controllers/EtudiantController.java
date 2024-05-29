package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.EtudiantDTO;
import odda.technologies.etudiantEvaluation.serviceImplementations.EtudiantService;
import odda.technologies.etudiantEvaluation.services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EtudiantDTO obtenirEtudiant(@PathVariable Long id) {
        return etudiantService.obtenirEtudiant(id);
    }

    @GetMapping("")
    public List<EtudiantDTO> listerEtudiants() {
        return etudiantService.listerEtudiants();
    }

    @PutMapping("/{id}")
    public EtudiantDTO mettreAJourEtudiant(@PathVariable Long id, @RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.mettreAJourEtudiant(id, etudiantDTO);
    }

    @DeleteMapping("/{id}")
    public void supprimerEtudiant(@PathVariable Long id) {
        etudiantService.supprimerEtudiant(id);
    }
}
