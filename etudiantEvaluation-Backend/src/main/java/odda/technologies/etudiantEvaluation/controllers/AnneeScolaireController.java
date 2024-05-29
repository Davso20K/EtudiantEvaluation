package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.AnneeScolaireDTO;
import odda.technologies.etudiantEvaluation.serviceImplementations.AnneeScolaireService;
import odda.technologies.etudiantEvaluation.services.IAnneeScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AnneeScolaireDTO obtenirAnneeScolaire(@PathVariable Long id) {
        return anneeScolaireService.obtenirAnneeScolaire(id);
    }
    @DeleteMapping("/{id}")
    public void supprimerAnneeScolaire(@PathVariable Long id) {
        anneeScolaireService.supprimerAnneeScolaire(id);
    }
}
