package odda.technologies.etudiantEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscriptionDTO {
    private long idInscription;
    private Date date;
    private StatutInscriptionEnum statut;
    private long etudiantId;
    private long anneeScolaireId;
    private long filiereId;
    private EtudiantDTO etudiantDTO;
    private  AnneeScolaireDTO anneeScolaireDTO;
    private  FiliereDTO filiereDTO;
}
