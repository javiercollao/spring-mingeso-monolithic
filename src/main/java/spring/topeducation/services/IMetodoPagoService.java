package spring.topeducation.services;

import spring.topeducation.entities.MetodoPagoEntity;

import java.util.List;

public interface IMetodoPagoService {
    public MetodoPagoEntity crearMetodoPago(MetodoPagoEntity metodoPago);
    public List<MetodoPagoEntity> listarMetodosDePago();
    public MetodoPagoEntity obtenerMetodoPagoPorId(Long id);
}
