package spring.topeducation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.topeducation.dto.EstudianteDTO;
import spring.topeducation.entities.CategoriaEntity;
import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.entities.MetodoPagoEntity;
import spring.topeducation.services.impl.CategoriaService;
import spring.topeducation.services.impl.CuotaService;
import spring.topeducation.services.impl.EstudianteService;
import spring.topeducation.services.impl.MetodoPagoService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    @Autowired
    EstudianteService estudianteService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    MetodoPagoService metodoPagoService;

    @Autowired
    CuotaService cuotaService;



    @GetMapping("/")
    public String index(
            Model model
    ){
        List<EstudianteEntity> estudiantesList = estudianteService.listarEstudiantes();
        List<CategoriaEntity> categoriasList = categoriaService.listarCategorias();
        List<MetodoPagoEntity> metodosDePagoList = metodoPagoService.listarMetodosDePago();
        model.addAttribute("estudiantes", estudiantesList);
        model.addAttribute("metodospago", metodosDePagoList);
        model.addAttribute("categorias", categoriasList);
        return "index";
    }

    @PostMapping("/guardar-estudiante")
    public String guardarEstudiante(
            @RequestParam("rut") String rut,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("anoEgreso") String anoEgreso,
            @RequestParam("nombreColegio") String nombreColegio,
            @RequestParam("idCategoria") String idCategoria,
            @RequestParam("idMetodoPago") String idMetodoPago
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(fechaNacimiento, formatter);
        EstudianteDTO estudiante = EstudianteDTO.builder()
                .rut(rut)
                .nombre(nombre)
                .apellidos(apellidos)
                .fecha_nacimiento(parsedDate)
                .año_egreso(anoEgreso)
                .nombre_colegio(nombreColegio)
                .id_categoria(idCategoria)
                .id_metodo_pago(idMetodoPago)
                .build();

        estudianteService.crearEstudiante(estudiante);
        return "redirect:/";
    }

    @GetMapping("/cuotas/{id}")
    public String cuotas(
            @PathVariable(value = "id") Long id,
            Model model
    ){
        List<CuotaEntity> cuotasList = cuotaService.obtenerCuotasDePagoEstudiante(id);
        List<CuotaEntity> pendientesList = cuotasList.stream().filter(cuota -> Objects.equals(cuota.getStatus_cuota(), "Pendiente")).toList();
        List<CuotaEntity> pagadoList = cuotasList.stream().filter(cuota -> Objects.equals(cuota.getStatus_cuota(), "Pagado")).toList();
        model.addAttribute("cuotas", pendientesList);
        model.addAttribute("pagados", pagadoList);
        return "cuotas";
    }

    @GetMapping("/pagar/{id}")
    public String pagos(
            @PathVariable(value = "id") Long id
    ){
        CuotaEntity cuota = cuotaService.obtenerCuota(id);
        cuotaService.confirmarPagoDeCuota(id);
        return "redirect:/cuotas/"+cuota.getEstudiante().getId_estudiante();
    }

    @GetMapping("/actualizar-cuota/{id}")
    public String actualizarCuota(
            @PathVariable(value = "id") Long id
    ){
        CuotaEntity cuota = cuotaService.obtenerCuota(id);
        cuotaService.aplicarIntereses(id);
        return "redirect:/cuotas/"+cuota.getEstudiante().getId_estudiante();
    }

}
