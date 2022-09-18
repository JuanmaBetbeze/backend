package com.example.demo.controllers.empleado;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Mensaje;
import com.example.demo.models.historial.*;
import com.example.demo.services.dispositivo.DispositivoService;
import com.example.demo.services.empleado.EmpleadoService;
import com.example.demo.services.empleado.HistorialEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class HistorialEmpleadoController {
    @Autowired
    HistorialEmpleadoService historialEmpleadoService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping("historialEmpleado/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody HistorialEmpleadoNuevo historialEmpleadoNuevo,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        HistorialEmpleado historialEmpleado=new HistorialEmpleado(dispositivoService.findDispositivo(
                historialEmpleadoNuevo.getIdDispo()),historialEmpleadoNuevo.getFechaAsignacion()
                ,historialEmpleadoNuevo.getFechaDesincronizacion(),historialEmpleadoNuevo.getEjecutor());
        historialEmpleadoService.save(historialEmpleado);
        return new ResponseEntity<>(new Mensaje("Historial agregado con exito"),HttpStatus.CREATED);
    }

    @GetMapping("historialEmpleado/listar/{id}")
    public ResponseEntity<List<HistorialEmpleadoListar>> listarHistorialDispositivo(@PathVariable("id")int id){
        Empleado empleado= empleadoService.getOne((long)id).get();
        List<HistorialEmpleado> listarHistorial = empleado.getHistorialEmpleados();
        List<HistorialEmpleadoListar> lista=new ArrayList<>();
        if(!listarHistorial.isEmpty()) {
            listarHistorial.forEach(historial -> lista.add(crearHistorialEmpleadoListar(historial)));
            lista.sort(Comparator.comparing(HistorialEmpleadoListar::getFechaDesincronizacion).reversed());
            lista.stream().filter(historialDispositivoListar -> Objects.equals(historialDispositivoListar.getFechaDesincronizacion(), LocalDate.of(50000, 10, 15)))
                    .forEach(historialDispositivoListar -> historialDispositivoListar.setFechaDesincronizacion(null));
        }
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    public HistorialEmpleadoListar crearHistorialEmpleadoListar(HistorialEmpleado historialEmpleado){
        Dispositivo dispositivo=historialEmpleado.getDispositivo();
        if(historialEmpleado.getFechaDesincronizacion()==null) {
            return new HistorialEmpleadoListar(dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),dispositivo.getModelo(),
                    dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(),dispositivo.getValor(),dispositivo.getAsegurado(),
                    historialEmpleado.getFechaAsignacion(),LocalDate.of(50000,10,15),historialEmpleado.getEjecutor());
        }
        return new HistorialEmpleadoListar(dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),dispositivo.getModelo(),
                dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(),dispositivo.getValor(),dispositivo.getAsegurado(),
                historialEmpleado.getFechaAsignacion(),historialEmpleado.getFechaDesincronizacion(),historialEmpleado.getEjecutor());
    }
}
