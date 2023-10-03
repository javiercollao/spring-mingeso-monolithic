package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.dto.EstudianteDTO;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.repository.CategoriaRepository;
import spring.topeducation.repository.EstudianteRepository;
import spring.topeducation.repository.MetodoPagoRepository;
import spring.topeducation.services.IEstudianteService;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    MetodoPagoRepository metodoPagoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public void crearEstudiante(EstudianteDTO estudiante) {

        /*
        Long idMetodoPago = Long.parseLong(estudiante.getId_metodo_pago());
        Long idCategoria = Long.parseLong(estudiante.getId_categoria());

        MetodoPagoEntity metodo = metodoPagoRepository.findById(idMetodoPago)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el método de pago con el ID proporcionado."));

        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría de colegio con el ID proporcionado."));

        System.out.println("Met2: "+ idMetodoPago + "nom: "+ metodo.getTipo_pago());
        System.out.println("Met2: "+ idCategoria + "nom: "+ categoria.getColegio_categoria());
        */

        EstudianteEntity nuevo_estudiante = new EstudianteEntity();
        nuevo_estudiante.setNombre(estudiante.getNombre());
        nuevo_estudiante.setApellidos(estudiante.getApellidos());
        nuevo_estudiante.setRut(estudiante.getRut());
        nuevo_estudiante.setFecha_nacimiento(estudiante.getFecha_nacimiento());
        nuevo_estudiante.setAño_egreso(Integer.parseInt(estudiante.getAño_egreso()));
        nuevo_estudiante.setNombre_colegio(estudiante.getNombre_colegio());

        /*

        List<EstudianteEntity> metodoEstudiantes = metodo.getEstudiantes();
        metodoEstudiantes.add(nuevo_estudiante);
        metodo.setEstudiantes(metodoEstudiantes);

        List<EstudianteEntity> categoriaEstudiantes = categoria.getEstudiantes();
        categoriaEstudiantes.add(nuevo_estudiante);
        categoria.setEstudiantes(categoriaEstudiantes);

        metodoPagoRepository.save(metodo);
        categoriaRepository.save(categoria);*/

        estudianteRepository.save(nuevo_estudiante);

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
