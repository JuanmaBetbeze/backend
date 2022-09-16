package com.example.demo.controllers.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.models.Dispositivo.MarcaModel;
import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Mensaje;
import com.example.demo.models.historial.HistorialDispositivo;
import com.example.demo.services.dispositivo.DispositivoService;
import com.example.demo.services.dispositivo.MarcaService;
import com.example.demo.services.dispositivo.TipoDispositivoService;
import com.example.demo.services.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class DispositivoController {
    @Autowired
    DispositivoService dispositivoService;
    @Autowired
    TipoDispositivoService tipoDispositivoService;
    @Autowired
    MarcaService marcaService;

    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("dispositivo/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody DispositivoNuevo dispositivoNuevo,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        if(dispositivoService.existeByModelo(dispositivoNuevo.getModelo())){
            return new ResponseEntity<>(new Mensaje("Ese dispositivo ya existe"), HttpStatus.BAD_REQUEST);
        }
        Dispositivo dispositivo=new Dispositivo(tipoDispositivoService.findByNombre(dispositivoNuevo.getTipo()),dispositivoNuevo.getNumeroDeSerie(),
                dispositivoNuevo.getModelo(),dispositivoNuevo.getIdDispo(),marcaService.findByNombre(dispositivoNuevo.getMarca()), dispositivoNuevo.getValor(), dispositivoNuevo.getAsegurado(),null, dispositivoNuevo.getEjecutor());
        dispositivoService.save(dispositivo);
        return new ResponseEntity<>(new Mensaje("Dispositivo agregado con exito"),HttpStatus.CREATED);
    }
    @GetMapping("/dispositivo")
    public ResponseEntity<List<DispositivoNuevo>> listarDispositivos(){
        List<Dispositivo> listaDispositivos = dispositivoService.listarDispositivos();
        List<DispositivoNuevo> lista=new ArrayList<>();
        listaDispositivos.forEach(dispositivo -> lista.add(crearDispositivoNuevo(dispositivo)));
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/dispositivo/SAsignar")
    public ResponseEntity<List<DispositivoNuevo>> listarDispositivosSinAsignar(){
        List<Dispositivo> listaDispositivos = dispositivoService.findByEmpleadoActual(null);
        List<DispositivoNuevo> lista=new ArrayList<>();
        listaDispositivos.forEach(dispositivo -> lista.add(crearDispositivoNuevo(dispositivo)));
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    public DispositivoNuevo crearDispositivoNuevo(Dispositivo dispositivo){
        if (dispositivo.getEmpleadoActual()==null){
            return new DispositivoNuevo(dispositivo.getId(),dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),
                    dispositivo.getModelo(),dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(), dispositivo.getValor(),
                    dispositivo.getAsegurado(), (long) 0, dispositivo.getEjecutor());
        }
        return new DispositivoNuevo(dispositivo.getId(),dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),
                dispositivo.getModelo(),dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(),
                dispositivo.getValor(), dispositivo.getAsegurado(),dispositivo.getEmpleadoActual().getId(), dispositivo.getEjecutor());
    }

    @GetMapping("/dispositivo/detail/{id}")
    public ResponseEntity<DispositivoNuevo> getById(@PathVariable("id") int id){
        if(!dispositivoService.existsById(((long) id)))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Dispositivo dispositivo = dispositivoService.findDispositivo((long)id);
        DispositivoNuevo dispositivoNuevo=crearDispositivoNuevo(dispositivo);
        return new ResponseEntity(dispositivoNuevo, HttpStatus.OK);
    }
    @PutMapping("/dispositivo/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DispositivoNuevo dispositivoNuevo){
        if(!dispositivoService.existsById((long)id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(dispositivoService.existeByModelo(dispositivoNuevo.getModelo())){
            if(!Objects.equals(dispositivoService.findDispositivoByModelo(dispositivoNuevo.getModelo()).getId(), dispositivoNuevo.getId()))
                return new ResponseEntity<>(new Mensaje("El modelo ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        Dispositivo dispositivo = dispositivoService.findDispositivo((long)id);
        dispositivo.setTipo(tipoDispositivoService.findByNombre(dispositivoNuevo.getTipo()));
       dispositivo.setNumeroDeSerie(dispositivoNuevo.getNumeroDeSerie());
       dispositivo.setAsegurado(dispositivoNuevo.getAsegurado());
       dispositivo.setMarca(marcaService.findByNombre(dispositivoNuevo.getMarca()));
       dispositivo.setModelo(dispositivoNuevo.getModelo());
       dispositivo.setIdDispo(dispositivoNuevo.getIdDispo());
       dispositivo.setValor(dispositivoNuevo.getValor());
       dispositivoService.save(dispositivo);
        return new ResponseEntity(new Mensaje("dispositivo actualizado"), HttpStatus.OK);
    }

    @PostMapping("/dispositivo/delete")
    public ResponseEntity<?> eliminarDispositivo(@Valid@RequestBody int id){
        if (!dispositivoService.existsById((long)id))
            return new ResponseEntity<>(new Mensaje("Ese dispositivo no existe"),HttpStatus.BAD_REQUEST);
        Dispositivo dispositivo= dispositivoService.findDispositivo((long) id);
        if(!dispositivo.getHistorialDispositivo().isEmpty()) {
            List<HistorialDispositivo> historialDispositivos = dispositivo.getHistorialDispositivo();
            Empleado empleado = historialDispositivos.stream().filter(historialDispositivo -> historialDispositivo.getFechaDesincronizacion() == null)
                    .findFirst().get().getEmpleado();
            List<Dispositivo> dispositivos = empleado.getDispositivos();
            dispositivos.remove(dispositivo);
            empleado.setDispositivos(dispositivos);
            empleadoService.save(empleado);
        }
        dispositivoService.eliminarDispositivo((long)id);
        return new ResponseEntity<>(new Mensaje("Empleado eliminado con exito"),HttpStatus.CREATED);
    }
    @PostMapping("/dispositivo/filtrar")
    public ResponseEntity<List<DispositivoNuevo>> filtrar(@RequestBody List<String> filtrarList){
        String filtrado=filtrarList.get(0);
        String valor=filtrarList.get(1);
        List<Dispositivo>dispositivos=new ArrayList<>();
        if(Objects.equals(filtrado, "tipo")){
            TipoDispositivoModel tipo=tipoDispositivoService.findByNombre(valor);
            dispositivos=dispositivoService.findByTipo(tipo);
        }
        if(Objects.equals(filtrado,"numeroDeSerie")){
            dispositivos=dispositivoService.findByNumeroDeSerie(valor);
        }
        if(Objects.equals(filtrado,"modelo")){
            dispositivos=dispositivoService.findByModelo(valor);
        }
        if(Objects.equals(filtrado,"idDispo")){
            dispositivos=dispositivoService.findByIdDispo(valor);
        }
        if(Objects.equals(filtrado,"marca")){
            MarcaModel marca=marcaService.findByNombre(valor);
            dispositivos=dispositivoService.findByMarca(marca);
        }
        if(Objects.equals(filtrado,"valor")){
            dispositivos=dispositivoService.findByValor(Float.valueOf(valor));
        }
        if(Objects.equals(filtrado,"asegurado")){
            boolean valorBoolean= Objects.equals(valor, "SI");
            dispositivos=dispositivoService.findByAsegurado(valorBoolean);
        }
        if(Objects.equals(filtrado,"duenio")){
            if(Objects.equals(valor,"asignado")){
                dispositivos=dispositivoService.findByEmpleadoActualNotNull();
            }
            if(Objects.equals(valor,"sinasignar")){
                dispositivos=dispositivoService.findByEmpleadoActualNull();
            }
        }
        if (Objects.equals(filtrado,"listar")){
            dispositivos=dispositivoService.listarDispositivos();
        }
        List<DispositivoNuevo> lista=new ArrayList<>();
        dispositivos.forEach(dispositivo -> lista.add(crearDispositivoNuevo(dispositivo)));

        return new ResponseEntity(lista, HttpStatus.OK);
    }

}
