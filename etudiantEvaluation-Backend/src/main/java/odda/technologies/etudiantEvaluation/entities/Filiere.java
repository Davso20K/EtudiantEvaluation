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
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFiliere;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String libelle;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<Inscription> listInscriptions;
}
