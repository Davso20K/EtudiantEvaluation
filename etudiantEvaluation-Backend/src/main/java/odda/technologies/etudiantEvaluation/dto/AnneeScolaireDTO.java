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
public class AnneeScolaireDTO {
    private long idAnneScolaire;

    private String libelle;
    private boolean etat;
}
