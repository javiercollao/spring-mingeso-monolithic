package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.CategoriaEnum;
import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.entities.MetodoPagoEnum;
import spring.topeducation.repository.CuotaRepository;
import spring.topeducation.repository.EstudianteRepository;
import spring.topeducation.services.ICuotaService;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CuotaService implements ICuotaService {
    @Autowired
    CuotaRepository cuotaRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    PagoService pagoService;

    @Override
    public List<CuotaEntity> obtenerCuotasDePagoEstudiante(Long id_estudiante) {
        EstudianteEntity estudiante = estudianteRepository.findById(id_estudiante)
                .orElseThrow(() -> new NoSuchElementException("No se encontró al estudiante con el ID proporcionado."));
        List<CuotaEntity> cuotas =  cuotaRepository.findAllByEstudiante(estudiante);
        return cuotas;
    }

    public Double obtenerDescuentoPorCategoria(CategoriaEnum categoriaEnum) {
        switch (categoriaEnum) {
            case PRIVADO:
                return 0.0;
            case SUBVENCIONADO:
                return 0.1;
            case MUNICIPAL:
                return 0.2;
            default:
                return 0.0;
        }
    }

    public Double obtenerDescuentoPorMetodoDePago(MetodoPagoEnum metodoPagoEnum){
        switch (metodoPagoEnum) {
            case CUOTAS:
                return 0.0;
            case CONTADO:
                return 0.5;
            default:
                return 0.0;
        }
    }

    public Double obtenerDescuentoPorAnioDeEgreso(Integer anioEgreso){
        Year year = Year.now();
        Integer añoActual = year.getValue();
        Integer diff = añoActual - anioEgreso;
        Double dcto;

        if (diff < 1){
            dcto = 0.15;
        } else if (diff <= 2 ) {
            dcto = 0.08;
        } else if (diff <= 4 ) {
            dcto = 0.04;
        } else {
            dcto = 0.0;
        }
        return dcto;
    }

    public Integer obtenerNumeroDeCuotas(MetodoPagoEnum metodoPagoEnum, CategoriaEnum categoriaEnum){
        switch (metodoPagoEnum) {
            case CUOTAS:
                return obtenerNumeroDeCuotasPorCategoria(categoriaEnum);
            case CONTADO:
                return 1;
            default:
                return 1;
        }
    }

    public Integer obtenerNumeroDeCuotasPorCategoria(CategoriaEnum categoriaEnum){
        switch (categoriaEnum) {
            case MUNICIPAL:
                return 10;
            case SUBVENCIONADO:
                return 7;
            case PRIVADO:
                return 4;
            default:
                return 1;
        }
    }

    private Double calcularDescuentoTotal(EstudianteEntity estudiante) {
        Double descuentoCategoria = obtenerDescuentoPorCategoria(estudiante.getCategoria());
        Double descuentoMetodoPago = obtenerDescuentoPorMetodoDePago(estudiante.getMetodoPago());
        Double descuentoPorEgreso = obtenerDescuentoPorAnioDeEgreso(estudiante.getAnio_egreso());
        return descuentoCategoria + descuentoMetodoPago + descuentoPorEgreso;
    }

    private Integer calcularArancelConDescuento(Double descuentoTotal) {
        double totalArancel = 1500000.0;
        Double aplicacionDescto = totalArancel * (1 - descuentoTotal);
        Integer arancelConDescuento = aplicacionDescto.intValue();
        return arancelConDescuento;
    }


    @Override
    public void generarCuotasDePagoArancel(Long id_estudiante) {
        EstudianteEntity estudiante = estudianteRepository.findById(id_estudiante)
                .orElseThrow(() -> new NoSuchElementException("No se encontró al estudiante con el ID proporcionado."));

        Double descuentoTotal = calcularDescuentoTotal(estudiante);
        Integer totalArancelConDescuento = calcularArancelConDescuento(descuentoTotal);
        Integer numeroCuotas = obtenerNumeroDeCuotas(estudiante.getMetodoPago(),estudiante.getCategoria());

        Integer montoCuota = totalArancelConDescuento / numeroCuotas;
        Integer remanente = totalArancelConDescuento % numeroCuotas;

        LocalDate fechaInicioPagar = LocalDate.now().withDayOfMonth(5);
        LocalDate fechaInicioDeuda = LocalDate.now().withDayOfMonth(10);

        for (Integer i = 0; i < numeroCuotas; i++) {
            CuotaEntity cuotaArancel = crearCuotaArancel(montoCuota, remanente, fechaInicioPagar, fechaInicioDeuda, estudiante, numeroCuotas, i);
            cuotaRepository.save(cuotaArancel);
        }
    }


    private CuotaEntity crearCuotaArancel(Integer montoCuota, Integer remanente, LocalDate fechaInicioPagar, LocalDate fechaInicioDeuda, EstudianteEntity estudiante, Integer numeroCuotas, Integer i) {
        Integer numero = i + 1;
        CuotaEntity cuotaArancel = new CuotaEntity();
        if (i == numeroCuotas - 1 && remanente > 0) {
            cuotaArancel.setValor_cuota(montoCuota + remanente);
        } else {
            cuotaArancel.setValor_cuota(montoCuota);
        }
        cuotaArancel.setAsunto("Cuota Arancel " + numero);
        cuotaArancel.setStatus_cuota("Pendiente");
        cuotaArancel.setFecha_inicio_pagar(fechaInicioPagar.plusMonths(i));
        cuotaArancel.setFecha_inicio_deuda(fechaInicioDeuda.plusMonths(i));
        cuotaArancel.setEstudiante(estudiante);
        return cuotaArancel;
    }


    @Override
    public void crearCuotaMatricula(Long id_estudiante) {
        EstudianteEntity estudiante = estudianteRepository.findById(id_estudiante)
                .orElseThrow(() -> new NoSuchElementException("No se encontró al estudiante con el ID proporcionado."));

        Integer totalMatricula = 70000;

        CuotaEntity cuotaMatricula = new CuotaEntity();
        cuotaMatricula.setValor_cuota(totalMatricula);
        cuotaMatricula.setAsunto("Matrícula");
        cuotaMatricula.setStatus_cuota("Pendiente");
        cuotaMatricula.setFecha_inicio_pagar(LocalDate.now().withDayOfMonth(5));
        cuotaMatricula.setFecha_inicio_deuda(LocalDate.now().withDayOfMonth(10));
        cuotaMatricula.setEstudiante(estudiante);
        cuotaRepository.save(cuotaMatricula);
    }

    @Override
    public CuotaEntity obtenerCuota(Long id) {
        CuotaEntity cuota = cuotaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la cuota con el ID proporcionado."));
        return cuota;
    }

    @Override
    public void aplicarIntereses(Long id) {
        CuotaEntity cuota = cuotaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la cuota con el ID proporcionado."));

        Double interes = calculoDeIntereses(id);
        Double totalCuota = cuota.getValor_cuota().doubleValue();
        Double aplicacionInteres = totalCuota * (1 + interes);
        Integer total = aplicacionInteres.intValue();

        cuota.setValor_cuota(total);
        cuotaRepository.save(cuota);
    }


    @Override
    public Double calculoDeIntereses(Long id) {
        CuotaEntity cuota = cuotaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la cuota con el ID proporcionado."));

        if (!Objects.equals(cuota.getAsunto(), "Matrícula")) {
            LocalDate fechaInicio = cuota.getFecha_inicio_pagar();
            LocalDate fechaPlazoFinal = cuota.getFecha_inicio_deuda();
            LocalDate fechaActual = LocalDate.now();

            boolean fueraDelIntervalo = fechaActual.isAfter(fechaPlazoFinal);

            if (fueraDelIntervalo) {
                Long mesesDeAtraso = ChronoUnit.MONTHS.between(fechaInicio, fechaActual);
                if (mesesDeAtraso < 0) {
                     mesesDeAtraso =  0L;
                }

                Double interes = 0.0;
                if (mesesDeAtraso == 0) {
                    interes = 0.0;
                } else if (mesesDeAtraso == 1) {
                    interes = 0.03;
                } else if (mesesDeAtraso == 2) {
                    interes = 0.06;
                } else if (mesesDeAtraso == 3) {
                    interes = 0.09;
                } else if (mesesDeAtraso > 3) {
                    interes = 0.15;
                }
                return interes;
            }
        }

        return 0.0;
    }


    @Override
    public void confirmarPagoDeCuota(Long id) {
        CuotaEntity cuota = cuotaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la cuota con el ID proporcionado."));
        cuota.setStatus_cuota("Pagado");
        cuotaRepository.save(cuota);
        pagoService.crearPago(cuota.getEstudiante(), cuota.getValor_cuota());
    }

}
