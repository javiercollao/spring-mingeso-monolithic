package spring.topeducation.services;

import spring.topeducation.entities.CuotaEntity;

import java.util.List;

public interface ICuotaService {
    public List<CuotaEntity> obtenerCuotasDePagoEstudiante(Long id_estudiante, Integer year, String status);
    public CuotaEntity generarCuotaDePago(CuotaEntity cuota);
    public CuotaEntity actualizarEstadoCuota(CuotaEntity cuota, Long id);
    public String calcularTotalArancelAPagar(Long id_estudiante, Integer year)
}
