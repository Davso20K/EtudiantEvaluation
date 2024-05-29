package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.AnneeScolaireDTO;
import odda.technologies.etudiantEvaluation.serviceImplementations.AnneeScolaireService;
import odda.technologies.etudiantEvaluation.services.IAnneeScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annees-scolaires")
public class AnneeScolaireController {
    @Autowired
    private IAnneeScolaireService anneeScolaireService;
    @GetMapping("")
    public List<AnneeScolaireDTO> listerAnneesScolaires() {
        return anneeScolaireService.listerAnneesScolaires();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenirAnneeScolaire(@PathVariable Long id) {
        AnneeScolaireDTO anneeScolaire = anneeScolaireService.obtenirAnneeScolaire(id);
        if (anneeScolaire == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Année scolaire non trouvée avec l'ID : " + id);
        }
        return ResponseEntity.ok(anneeScolaire);
    }
    @DeleteMapping("/{id}")
    public void supprimerAnneeScolaire(@PathVariable Long id) {
        anneeScolaireService.supprimerAnneeScolaire(id);
    }
}
