package spring.topeducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="colegio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColegioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_colegio", unique=true, nullable = false)
    private Long id_colegio;
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estudiante")
    @JsonIgnore
    private List<EstudianteEntity> estudiantes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;
}

