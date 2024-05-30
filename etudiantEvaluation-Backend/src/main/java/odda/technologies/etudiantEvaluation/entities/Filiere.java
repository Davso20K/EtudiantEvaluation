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
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFiliere;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String libelle;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeMiseAJour;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<Inscription> listInscriptions;
}
