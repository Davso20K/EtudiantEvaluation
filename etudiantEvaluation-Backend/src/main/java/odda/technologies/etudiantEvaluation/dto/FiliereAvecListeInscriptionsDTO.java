package odda.technologies.etudiantEvaluation.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class FiliereAvecListeInscriptionsDTO extends FiliereDTO{
    private List<InscriptionDTO> listInscriptions;


}
