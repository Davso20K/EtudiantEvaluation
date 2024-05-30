package odda.technologies.etudiantEvaluation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AnneeScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnneScolaire;

    private String libelle;

    private boolean etat;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeMiseAJour;

    @OneToMany(mappedBy="anneeScolaire",cascade = CascadeType.ALL)
    private List<Inscription> listInscriptions;

}
