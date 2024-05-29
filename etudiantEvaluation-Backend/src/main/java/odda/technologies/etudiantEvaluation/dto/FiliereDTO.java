package odda.technologies.etudiantEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiliereDTO {
    private long idFiliere;
    private String code;
    private String libelle;
    private List<InscriptionDTO> listInscriptions;
}
