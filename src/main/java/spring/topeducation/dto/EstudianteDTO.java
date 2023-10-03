package spring.topeducation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class EstudianteDTO {
    private String nombre;
    private String apellidos;
    private String rut;
    private String año_egreso;
    private LocalDate fecha_nacimiento;
    private String nombre_colegio;
    private String id_categoria;
    private String id_metodo_pago;
}
