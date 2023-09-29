package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.repository.CuotaRepository;
import spring.topeducation.services.ICuotaService;

import java.util.List;

@Service
public class CuotaService implements ICuotaService {
    @Autowired
    CuotaRepository cuotaRepository;
    @Override
    public List<CuotaEntity> obtenerCuotasDePagoEstudiante(Long id_estudiante, Integer year, String status) {
        return null;
    }

    @Override
    public CuotaEntity generarCuotaDePago(CuotaEntity cuota) {
        return null;
    }

    @Override
    public CuotaEntity actualizarEstadoCuota(CuotaEntity cuota, Long id) {
        return null;
    }

    @Override
    public String calcularTotalArancelAPagar(Long id_estudiante, Integer year) {
        return null;
    }
}
