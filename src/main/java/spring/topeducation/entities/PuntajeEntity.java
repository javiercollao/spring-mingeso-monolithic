package spring.topeducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="puntaje")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PuntajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_puntaje", unique=true, nullable = false)
    private Long id_puntaje;
    private int valor_puntaje;
    private Date fecha_puntaje;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity estudiante;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity estudiante;
}


