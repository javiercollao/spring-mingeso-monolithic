package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.ExcedenteEntity;
import spring.topeducation.repository.ExcedenteRepository;
import spring.topeducation.services.IExcedenteService;

@Service
public class ExcedenteService implements IExcedenteService {
    @Autowired
    ExcedenteRepository excedenteRepository;

    @Override
    public ExcedenteEntity crearExcedente(ExcedenteEntity excedente) {
        return null;
    }

    @Override
    public String calcularSaldoPorPagar(Long Estudiante, String status) {
        return null;
    }
}
