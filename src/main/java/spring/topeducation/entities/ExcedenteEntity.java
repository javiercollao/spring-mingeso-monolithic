package spring.topeducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="excedentes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcedenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_excedente", unique=true, nullable = false)
    private Long id_excedente;
    private Integer monto;
    private Date fecha_de_pago;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity estudiante;
}
