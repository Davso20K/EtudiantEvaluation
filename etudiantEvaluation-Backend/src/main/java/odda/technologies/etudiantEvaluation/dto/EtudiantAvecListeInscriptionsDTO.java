package odda.technologies.etudiantEvaluation.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import odda.technologies.etudiantEvaluation.Enumerations.SexeEnum;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class EtudiantAvecListeInscriptionsDTO extends EtudiantDTO{
    private List<InscriptionDTO> listInscriptions;

}
