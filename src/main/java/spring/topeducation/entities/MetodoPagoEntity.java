package spring.topeducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="metodopago")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetodoPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_metodo_pago", unique=true, nullable = false)
    private Long id_metodo_pago;
    private String tipo_pago;

    @OneToOne(mappedBy = "metodopago")
    private EstudianteEntity estudiante;
}