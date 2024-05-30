package odda.technologies.etudiantEvaluation.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AnneeScolaireAvecListeInscriptionsDTO extends AnneeScolaireDTO {
    private List<InscriptionDTO> listInscriptions;

}
