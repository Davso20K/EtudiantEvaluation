package odda.technologies.etudiantEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class InscriptionAvecParentsInfosDTO extends InscriptionDTO{
    private EtudiantDTO etudiantDTO;
    private  AnneeScolaireDTO anneeScolaireDTO;
    private  FiliereDTO filiereDTO;
}
