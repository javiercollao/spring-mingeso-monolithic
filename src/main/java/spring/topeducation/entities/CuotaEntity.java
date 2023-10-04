package spring.topeducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String asunto;
    private String status_cuota;
    private Integer valor_cuota;
    private LocalDate fecha_inicio_pagar;
    private LocalDate fecha_inicio_deuda;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity estudiante;
}
