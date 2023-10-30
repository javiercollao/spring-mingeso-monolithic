package spring.topeducation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.topeducation.dto.EstudianteDTO;
import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.entities.EstudianteEntity;
import spring.topeducation.entities.PuntajeEntity;
import spring.topeducation.services.impl.CuotaService;
import spring.topeducation.services.impl.EstudianteService;
import spring.topeducation.services.impl.ExcelService;
import spring.topeducation.utils.ExcelHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    @Autowired
    EstudianteService estudianteService;

    @Autowired
    CuotaService cuotaService;

    @Autowired
    ExcelService excelService;

    @GetMapping("/")
    public String index(
            Model model
    ){
        List<EstudianteEntity> estudiantesList = estudianteService.listarEstudiantes();

        List<List<String>> categoriasList = new ArrayList<>();
        categoriasList.add(new ArrayList<>(List.of("MUNICIPAL", "Municipal")));
        categoriasList.add(new ArrayList<>(List.of("SUBVENCIONADO", "Subvencionado")));
        categoriasList.add(new ArrayList<>(List.of("PRIVADO", "Privado")));

        List<List<String>> metodosDePagoList = new ArrayList<>();
        metodosDePagoList.add(new ArrayList<>(List.of("CONTADO", "Al contado")));
        metodosDePagoList.add(new ArrayList<>(List.of("CUOTAS", "En cuotas")));

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
            @RequestParam("anioEgreso") String anioEgreso,
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
                .anio_egreso(anioEgreso)
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


    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);
                return "redirect:/puntajes";
            } catch (Exception e) {
                // message = "No se pudieron subir los datos: " + file.getOriginalFilename() + "!";
                return "redirect:/puntajes";
            }
        }

        // message = "Please upload an excel file!";
        return "redirect:/puntajes";
    }

    @GetMapping("/puntajes")
    public String obtenerTodosLosPuntajes(
            Model model
    ) {
        try {
            List<PuntajeEntity> puntajes = excelService.obtenerTodosLosPuntajes();
            model.addAttribute("puntajes", puntajes);
            return "redirect:/puntajes";
        } catch (Exception e) {
            return "redirect:/puntajes";
        }
    }

}
