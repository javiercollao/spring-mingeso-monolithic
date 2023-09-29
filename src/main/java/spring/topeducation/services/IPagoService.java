package spring.topeducation.services;

import spring.topeducation.entities.PagoEntity;

import java.util.List;

public interface IPagoService {
    public PagoEntity registrarPago(PagoEntity pago);
    public String CalcularTotalPagago(Long id_estudiante, Integer year);

    public PagoEntity obtenerUltimoPagoRegistrado(Long id_estudiante, Integer year);

}
