package odda.technologies.etudiantEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FiliereDTO {
    private long idFiliere;
    private String code;
    private String libelle;
}
