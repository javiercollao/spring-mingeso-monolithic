package spring.topeducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categorias")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria", unique=true, nullable = false)
    private Long id_categoria;
    private String colegio_categoria;
    private Double descuento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estudiante")
    @JsonIgnore
    private List<EstudianteEntity> estudiantes = new ArrayList<>();

}
