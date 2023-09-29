package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.repository.EstudianteRepository;
import spring.topeducation.services.IEstudianteService;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public EstudianteEntity crearEstudiante(EstudianteEntity estudiante) {
        return null;
    }

    @Override
    public List<EstudianteEntity> obtenerEstudiantePorRut(String rut) {
        return null;
    }

    @Override
    public EstudianteEntity agregarMetodoPagoEstudiante(EstudianteEntity estudiante) {
        return null;
    }
}
