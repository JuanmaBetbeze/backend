package com.example.demo.controllers.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Mensaje;
import com.example.demo.models.historial.HistorialDispositivo;
import com.example.demo.models.historial.HistorialDispositivoListar;
import com.example.demo.models.historial.HistorialDispositivoNuevo;
import com.example.demo.services.dispositivo.DispositivoService;
import com.example.demo.services.dispositivo.HistorialDispositivoService;
import com.example.demo.services.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class HistorialDispositivoController {
    @Autowired
    HistorialDispositivoService historialDispositivoService;
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping("historialDispositivo/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody HistorialDispositivoNuevo historialDispositivoNuevo,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        HistorialDispositivo historialDispositivo=new HistorialDispositivo(empleadoService.getOne(
                historialDispositivoNuevo.getIdEmpleado()).get(),historialDispositivoNuevo.getFechaAsignacion()
                ,historialDispositivoNuevo.getFechaDesincronizacion(),historialDispositivoNuevo.getEjecutor());
        historialDispositivoService.save(historialDispositivo);
        return new ResponseEntity<>(new Mensaje("Historial agregado con exito"),HttpStatus.CREATED);
    }

    @GetMapping("historialDispositivo/listar/{id}")
    public ResponseEntity<List<HistorialDispositivoListar>> listarHistorialDispositivo(@PathVariable("id")int id){
        Dispositivo dispositivo= dispositivoService.findDispositivo((long)id);
        List<HistorialDispositivo> listarHistorial = dispositivo.getHistorialDispositivo();
        List<HistorialDispositivoListar> lista=new ArrayList<>();
        if(!listarHistorial.isEmpty()) {
            listarHistorial.forEach(historial -> lista.add(crearHistorialDispositivoListar(historial)));
            lista.sort(Comparator.comparing(HistorialDispositivoListar::getFechaDesincronizacion).reversed());
            lista.stream().filter(historialDispositivoListar -> Objects.equals(historialDispositivoListar.getFechaDesincronizacion(), LocalDate.of(50000, 10, 15)))
                    .forEach(historialDispositivoListar -> historialDispositivoListar.setFechaDesincronizacion(null));
        }
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    public HistorialDispositivoListar crearHistorialDispositivoListar(HistorialDispositivo historialDispositivo){
        Empleado empleado=historialDispositivo.getEmpleado();
        if(historialDispositivo.getFechaDesincronizacion()==null) {
            return new HistorialDispositivoListar(empleado.getNombre(), empleado.getApellido(), empleado.getIdEmpleado(), empleado.getSector().getSector(),
                    empleado.getPuesto().getPuesto(), empleado.getDni(), historialDispositivo.getFechaAsignacion(),
                    LocalDate.of(50000,10,15),historialDispositivo.getEjecutor());
        }
        return new HistorialDispositivoListar(empleado.getNombre(), empleado.getApellido(), empleado.getIdEmpleado(), empleado.getSector().getSector(),
                empleado.getPuesto().getPuesto(), empleado.getDni(), historialDispositivo.getFechaAsignacion()
                ,historialDispositivo.getFechaDesincronizacion(),historialDispositivo.getEjecutor());
    }

}
