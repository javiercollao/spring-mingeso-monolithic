package spring.topeducation.services;

import spring.topeducation.dto.EstudianteDTO;
import spring.topeducation.entities.EstudianteEntity;

import java.util.List;

public interface IEstudianteService {
    public void crearEstudiante(EstudianteDTO estudiante);
    public List<EstudianteEntity> listarEstudiantes();
    public EstudianteEntity obtenerEstudiantePorRut(String rut);

}
