package spring.topeducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="cuota")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cuota", unique=true, nullable = false)
    private Long id_cuota;
    private String status_cuota;
    private Integer valor_cuota;
    private Date fecha_inicio_pagar;
    private Date fecha_inicio_deuda;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity estudiante;
}
