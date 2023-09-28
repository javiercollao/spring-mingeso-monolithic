package spring.topeducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="estudiante")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estudiante", unique=true, nullable = false)
    private Long id_estudiante;
    private int rut;
    private String nombre;
    private String apellidos;
    private Date fecha_nacimiento;
    private int año_egreso;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_colegio")
    private ColegioEntity colegio;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cuota")
    @JsonIgnore
    private List<CuotaEntity> cuotas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pago")
    @JsonIgnore
    private List<PagoEntity> pagos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_puntaje")
    @JsonIgnore
    private List<PuntajeEntity> puntajes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "puntaje_id", referencedColumnName = "puntaje_id")
    private PuntajeEntity puntaje;
}





