package odda.technologies.etudiantEvaluation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToMany(mappedBy="anneeScolaire",cascade = CascadeType.ALL)
    private List<Inscription> listInscriptions;
}
