package spring.topeducation.utils;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import spring.topeducation.entities.CategoriaEntity;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.entities.MetodoPagoEntity;
import spring.topeducation.repository.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    MetodoPagoRepository metodoPagoRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    CuotaRepository cuotaRepository;
    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    PuntajeRepository puntajeRepository;
    @Autowired
    ExcedenteRepository excedenteRepository;

    @Autowired
    public DataSeeder(
            CategoriaRepository categoriaRepository,
            MetodoPagoRepository metodoPagoRepository,
            EstudianteRepository estudianteRepository,
            CuotaRepository cuotaRepository,
            PagoRepository pagoRepository,
            PuntajeRepository puntajeRepository,
            ExcedenteRepository excedenteRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.metodoPagoRepository = metodoPagoRepository;
        this.estudianteRepository = estudianteRepository;
        this.cuotaRepository = cuotaRepository;
        this.pagoRepository = pagoRepository;
        this.puntajeRepository = puntajeRepository;
        this.excedenteRepository = excedenteRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Categorias

        CategoriaEntity categoria_1 = new CategoriaEntity();
        categoria_1.setColegio_categoria("Municipal");
        categoria_1.setDescuento(0.2);
        categoriaRepository.save(categoria_1);

        CategoriaEntity categoria_2 = new CategoriaEntity();
        categoria_2.setColegio_categoria("Subvencionado");
        categoria_2.setDescuento(0.1);
        categoriaRepository.save(categoria_2);

        CategoriaEntity categoria_3 = new CategoriaEntity();
        categoria_3.setColegio_categoria("Privado");
        categoria_3.setDescuento(0.0);
        categoriaRepository.save(categoria_3);

        // Metodos de Pago

        MetodoPagoEntity metodoPago_1 = new MetodoPagoEntity();
        metodoPago_1.setTipo_pago("Al Contado");
        metodoPago_1.setDescuento(0.5);
        metodoPagoRepository.save(metodoPago_1);

        MetodoPagoEntity metodoPago_2 = new MetodoPagoEntity();
        metodoPago_2.setTipo_pago("En Cuotas");
        metodoPago_2.setDescuento(0.0);
        metodoPagoRepository.save(metodoPago_2);


        // Estudiante

        EstudianteEntity estudiante_1 = new EstudianteEntity();
        estudiante_1.setNombre("Javier");
        estudiante_1.setApellidos("Collao");
        estudiante_1.setRut("191772466");
        estudiante_1.setAño_egreso(2020);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse("2005-10-01");
        estudiante_1.setFecha_nacimiento(fecha);
        estudiante_1.setAño_egreso(2014);
        estudiante_1.setNombre_colegio("Colegio Prueba");








        // Cuotas

        // Pagos

        // Puntajes



    }



}

