package spring.topeducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="pago")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pago", unique=true, nullable = false)
    private Long id_pago;
    private Integer monto;
    private Date fecha_de_pago;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity estudiante;
}
