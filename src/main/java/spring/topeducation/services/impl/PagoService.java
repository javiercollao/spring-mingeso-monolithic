package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.PagoEntity;
import spring.topeducation.repository.PagoRepository;
import spring.topeducation.services.IPagoService;

@Service
public class PagoService implements IPagoService {
    @Autowired
    PagoRepository pagoRepository;

    @Override
    public PagoEntity registrarPago(PagoEntity pago) {
        return null;
    }

    @Override
    public String CalcularTotalPagago(Long id_estudiante, Integer year) {
        return null;
    }

    @Override
    public PagoEntity obtenerUltimoPagoRegistrado(Long id_estudiante, Integer year) {
        return null;
    }
}
