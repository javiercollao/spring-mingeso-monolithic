package spring.topeducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Double descuento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estudiante")
    @JsonIgnore
    private List<EstudianteEntity> estudiantes = new ArrayList<>();
}