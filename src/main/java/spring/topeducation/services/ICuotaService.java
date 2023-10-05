package spring.topeducation.services;

import spring.topeducation.entities.CuotaEntity;

import java.util.List;

public interface ICuotaService {
    public List<CuotaEntity> obtenerCuotasDePagoEstudiante(Long id_estudiante);
    public void generarCuotasDePagoArancel(Long id_estudiante);
    public void generarCuotaMatricula(Long id_estudiante);
    public CuotaEntity obtenerCuota(Long id);
    public void aplicarIntereses(Long id);
    public Double calculoDeIntereses(Long id);
    public void confirmarPagoDeCuota(Long id);
}
