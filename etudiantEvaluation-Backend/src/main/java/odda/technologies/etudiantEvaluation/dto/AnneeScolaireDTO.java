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
public class AnneeScolaireDTO {
    private long idAnneScolaire;

    private String libelle;
    private boolean etat;
    private List<InscriptionDTO> listInscriptions;
}
