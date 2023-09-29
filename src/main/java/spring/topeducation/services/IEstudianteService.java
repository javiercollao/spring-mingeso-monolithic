package spring.topeducation.services;

import spring.topeducation.entities.EstudianteEntity;

import java.util.List;

public interface IEstudianteService {
    public EstudianteEntity crearEstudiante(EstudianteEntity estudiante);
    public List<EstudianteEntity> obtenerEstudiantePorRut(String rut);
    public EstudianteEntity agregarMetodoPagoEstudiante(EstudianteEntity estudiante);

}
