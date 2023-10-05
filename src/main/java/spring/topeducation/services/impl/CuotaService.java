package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.entities.MetodoPagoEntity;
import spring.topeducation.repository.CuotaRepository;
import spring.topeducation.repository.EstudianteRepository;
import spring.topeducation.services.ICuotaService;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CuotaService implements ICuotaService {
    @Autowired
    CuotaRepository cuotaRepository;

    @Autowired
    EstudianteRepository estudianteRepository;
    @Override
    public List<CuotaEntity> obtenerCuotasDePagoEstudiante(Long id_estudiante) {
        EstudianteEntity estudiante = estudianteRepository.findById(id_estudiante)
                .orElseThrow(() -> new NoSuchElementException("No se encontró al estudiante con el ID proporcionado."));
        List<CuotaEntity> cuotas =  cuotaRepository.findAllByEstudiante(estudiante);
        return cuotas;
    }

    @Override
    public void generarCuotasDePagoArancel(Long id_estudiante) {
        EstudianteEntity estudiante = estudianteRepository.findById(id_estudiante)
                .orElseThrow(() -> new NoSuchElementException("No se encontró al estudiante con el ID proporcionado."));

        Double descuentoCategoria = estudiante.getCategoria().getDescuento();
        Double descuentoMetodoPago = estudiante.getMetodoPago().getDescuento();
        Double descuentoPorEgreso = calculoDescuentoPorEgreso(estudiante.getAño_egreso());
        Double descuento = descuentoCategoria + descuentoMetodoPago + descuentoPorEgreso;
        Double totalArancel = 1500000.0;
        Double aplicacionDescto = totalArancel * (1 - descuento);

        Integer totalArancelConDescuento = aplicacionDescto.intValue();

        Integer numeroCuotas;
        if (Objects.equals(estudiante.getMetodoPago().getTipo_pago(), "Al Contado")){
            numeroCuotas = 1;
        }else {
            numeroCuotas = estudiante.getCategoria().getNumero_cuotas();
        }

        Integer montoCuota = totalArancelConDescuento / numeroCuotas;
        Integer remanente = totalArancelConDescuento % numeroCuotas;

        LocalDate fechaInicioPagar = LocalDate.now().withDayOfMonth(5);
        LocalDate fechaInicioDeuda = LocalDate.now().withDayOfMonth(10);

        for (int i = 0; i < numeroCuotas; i++) {
            int numero = i + 1;
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
            cuotaRepository.save(cuotaArancel);
        }
    }

    public Double calculoDescuentoPorEgreso(Integer añoEgreso){
        Year year = Year.now();
        Integer añoActual = year.getValue();
        Integer diff = añoActual - añoEgreso;
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


    @Override
    public void generarCuotaMatricula(Long id_estudiante) {
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
    }


}
