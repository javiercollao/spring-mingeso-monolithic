package spring.topeducation.services;

import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.entities.EstudianteEntity;

import java.util.List;

public interface ICuotaService {
    public List<CuotaEntity> obtenerCuotasDePagoEstudiante(Long id_estudiante);
    public void generarCuotasDePagoArancel(Long id_estudiante);
    public void generarCuotaMatricula(Long id_estudiante);
    public CuotaEntity actualizarEstadoCuota(CuotaEntity cuota, Long id);
    public String calcularTotalArancelAPagar(Long id_estudiante, Integer year);
}
