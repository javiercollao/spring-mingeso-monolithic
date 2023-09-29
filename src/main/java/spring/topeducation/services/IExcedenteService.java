package spring.topeducation.services;

import spring.topeducation.entities.ExcedenteEntity;

public interface IExcedenteService {
    public ExcedenteEntity crearExcedente(ExcedenteEntity excedente);
    public String calcularSaldoPorPagar(Long Estudiante, String status);
}
