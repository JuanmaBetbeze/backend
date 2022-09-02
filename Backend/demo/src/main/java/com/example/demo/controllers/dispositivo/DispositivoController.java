package com.example.demo.controllers.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.EmpleadoNuevo;
import com.example.demo.models.Mensaje;
import com.example.demo.services.dispositivo.DispositivoService;
import com.example.demo.services.dispositivo.MarcaService;
import com.example.demo.services.dispositivo.TipoDispositivoService;
import com.example.demo.services.empleado.PuestoService;
import com.example.demo.services.empleado.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DispositivoController {
    @Autowired
    DispositivoService dispositivoService;
    @Autowired
    TipoDispositivoService tipoDispositivoService;
    @Autowired
    MarcaService marcaService;

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
                dispositivoNuevo.getModelo(),marcaService.findByNombre(dispositivoNuevo.getMarca()), dispositivoNuevo.getValor(), dispositivoNuevo.getAsegurado());
        dispositivoService.save(dispositivo);
        return new ResponseEntity<>(new Mensaje("Dispositivo agregado con exito"),HttpStatus.CREATED);
    }
    @GetMapping("/dispositivo")
    public ResponseEntity<List<DispositivoNuevo>> listarEmpleados(){
        List<Dispositivo> listaDispositivos = dispositivoService.listarDispositivos();
        List<DispositivoNuevo> lista=new ArrayList<>();
        listaDispositivos.forEach(dispositivo -> lista.add(new DispositivoNuevo(dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),
                dispositivo.getModelo(),dispositivo.getMarca().getMarca(), dispositivo.getValor(), dispositivo.getAsegurado())));
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    /* @GetMapping("/empleados/detail/{id}")
    public ResponseEntity<EmpleadoNuevo> getById(@PathVariable("id") int id){
        if(!empleadoService.existsById(((long) id)))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Empleado empleado = empleadoService.getOne((long)id).get();
        EmpleadoNuevo empleadoNuevo=new EmpleadoNuevo(empleado.getId().intValue(),empleado.getNombre(),empleado.getApellido(),empleado.getIdEmpleado(),empleado.getSector().getSector(),
                empleado.getPuesto().getPuesto(),empleado.getDni());
        return new ResponseEntity(empleadoNuevo, HttpStatus.OK);
    }
    @PutMapping("/empleados/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EmpleadoNuevo empleadoNuevo){
        if(!empleadoService.existsById((long)id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(empleadoService.existsByNombre(empleadoNuevo.getNombre()) && empleadoService.getByNombre(empleadoNuevo.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        Empleado empleado = empleadoService.getOne((long)id).get();
        empleado.setNombre(empleadoNuevo.getNombre());
        empleado.setApellido(empleadoNuevo.getApellido());
        empleado.setIdEmpleado(empleadoNuevo.getIdEmpleado());
        empleado.setDni(empleadoNuevo.getDni());
        empleado.setSector(sectorService.findByNombre(empleadoNuevo.getSector()));
        empleado.setPuesto(puestoService.findByNombre(empleadoNuevo.getPuesto()));
        empleadoService.save(empleado);
        return new ResponseEntity(new Mensaje("empleado actualizado"), HttpStatus.OK);
    }

    @PostMapping("/empleados/delete")
    public ResponseEntity<?> eliminarEmpleado(@Valid@RequestBody int id){
        if (!empleadoService.existsById((long)id))
            return new ResponseEntity<>(new Mensaje("Ese empleado no existe"),HttpStatus.BAD_REQUEST);
        empleadoService.eliminarEmpleado((long)id);
        return new ResponseEntity<>(new Mensaje("Empleado eliminado con exito"),HttpStatus.CREATED);

    }
    */
}
