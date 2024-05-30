package odda.technologies.etudiantEvaluation.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import odda.technologies.etudiantEvaluation.Enumerations.SexeEnum;
import odda.technologies.etudiantEvaluation.entities.Inscription;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EtudiantDTO {

    private long idEtudiant;
    private String nom;
    private String prenom;
    private SexeEnum sexe;
    private Date dateNaissance;
    private String contact;
    private String email;

}
