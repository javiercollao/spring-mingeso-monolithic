package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.dto.EstudianteDTO;
import spring.topeducation.entities.*;
import spring.topeducation.repository.EstudianteRepository;
import spring.topeducation.services.IEstudianteService;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService{
    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    CuotaService cuotaService;

    @Override
    public void crearEstudiante(EstudianteDTO estudiante) {

        String categoriaNombre = estudiante.getId_categoria();
        CategoriaEnum categoria = CategoriaEnum.valueOf(categoriaNombre);

        String metodoNombre = estudiante.getId_metodo_pago();
        MetodoPagoEnum metodo = MetodoPagoEnum.valueOf(metodoNombre);

        EstudianteEntity nuevo_estudiante = new EstudianteEntity();
        nuevo_estudiante.setNombre(estudiante.getNombre());
        nuevo_estudiante.setApellidos(estudiante.getApellidos());
        nuevo_estudiante.setRut(estudiante.getRut());
        nuevo_estudiante.setFecha_nacimiento(estudiante.getFecha_nacimiento());
        nuevo_estudiante.setAnio_egreso(Integer.parseInt(estudiante.getAnio_egreso()));
        nuevo_estudiante.setNombre_colegio(estudiante.getNombre_colegio());
        nuevo_estudiante.setMetodoPago(metodo);
        nuevo_estudiante.setCategoria(categoria);
        estudianteRepository.save(nuevo_estudiante);

        cuotaService.crearCuotaMatricula(nuevo_estudiante.getId_estudiante());
        cuotaService.generarCuotasDePagoArancel(nuevo_estudiante.getId_estudiante());
    }


    @Override
    public List<EstudianteEntity> listarEstudiantes() {
        return (List<EstudianteEntity>) estudianteRepository.findAll();
    }


    @Override
    public EstudianteEntity obtenerEstudiantePorRut(String rut) {
        return estudianteRepository.findByRut(rut);
    }

}
