package odda.technologies.etudiantEvaluation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import odda.technologies.etudiantEvaluation.Enumerations.SexeEnum;

import java.util.List;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Etudiant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idEtudiant;
    private String nom;
    @Column(unique = true)
    private String prenom;
    @Enumerated(EnumType.STRING)
    private SexeEnum sexe;
    private Date dateNaissance;
    @Column(unique = true)
    private String contact;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "etudiant", cascade=CascadeType.ALL)
    private List<Inscription> listInscriptions;


}
