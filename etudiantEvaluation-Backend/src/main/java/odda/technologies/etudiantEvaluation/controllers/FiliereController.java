package odda.technologies.etudiantEvaluation.controllers;

import odda.technologies.etudiantEvaluation.dto.FiliereAvecListeInscriptionsDTO;
import odda.technologies.etudiantEvaluation.dto.FiliereDTO;
import odda.technologies.etudiantEvaluation.services.IFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filieres")
public class FiliereController {
    @Autowired
    private IFiliereService filiereService;

    @PostMapping("")
    public FiliereDTO creerFiliere(@RequestBody FiliereDTO filiereDTO) {
        return filiereService.creerFiliere(filiereDTO);
    }
    @GetMapping("/{id}")
    public FiliereAvecListeInscriptionsDTO obtenirFiliere(@PathVariable Long id) {
        return filiereService.obtenirFiliere(id);

    }
    @GetMapping("")
    public List<FiliereAvecListeInscriptionsDTO> listerFilieres() {
        return filiereService.listerFilieres();
    }

    @PutMapping("/{id}")
    public FiliereDTO mettreAJourFiliere(@PathVariable Long id, @RequestBody FiliereDTO filiereDTO) {
        return filiereService.mettreAJourFiliere(id, filiereDTO);
    }

    @DeleteMapping("/{id}")
    public void supprimerFiliere(@PathVariable Long id) {
        filiereService.supprimerFiliere(id);
    }

}
