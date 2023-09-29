package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.MetodoPagoEntity;
import spring.topeducation.repository.MetodoPagoRepository;
import spring.topeducation.services.IMetodoPagoService;

import java.util.List;

@Service
public class MetodoPagoService implements IMetodoPagoService {
    @Autowired
    MetodoPagoRepository metodoPagoRepository;

    @Override
    public MetodoPagoEntity crearMetodoPago(MetodoPagoEntity metodoPago) {
        return null;
    }

    @Override
    public List<MetodoPagoEntity> listarMetodosDePago() {
        return null;
    }

    @Override
    public MetodoPagoEntity obtenerMetodoPagoPorId(Long id) {
        return null;
    }
}
