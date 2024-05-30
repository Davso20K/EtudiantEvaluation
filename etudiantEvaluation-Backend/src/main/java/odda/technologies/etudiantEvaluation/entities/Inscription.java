package odda.technologies.etudiantEvaluation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import odda.technologies.etudiantEvaluation.Enumerations.StatutInscriptionEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"etudiant_id", "filiere_id"})})
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInscription;

    private Date date;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeMiseAJour;

    @Enumerated(EnumType.STRING)
    private StatutInscriptionEnum statut;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "anneeScolaire_id")
    private AnneeScolaire anneeScolaire;
}
