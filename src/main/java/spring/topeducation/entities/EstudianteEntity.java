package spring.topeducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private String rut;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private Integer anio_egreso;
    private String nombre_colegio;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "id_categoria")
    private CategoriaEnum categoria;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoEnum metodoPago;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    @JsonIgnore
    private List<CuotaEntity> cuotas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    @JsonIgnore
    private List<PagoEntity> pagos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    @JsonIgnore
    private List<ExcedenteEntity> excedentes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    @JsonIgnore
    private List<PuntajeEntity> puntajes = new ArrayList<>();


}





