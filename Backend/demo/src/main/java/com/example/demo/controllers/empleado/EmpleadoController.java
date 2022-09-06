package com.example.demo.controllers.empleado;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.EmpleadoNuevo;
import com.example.demo.models.Mensaje;
import com.example.demo.services.dispositivo.DispositivoService;
import com.example.demo.services.empleado.EmpleadoService;
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
import java.util.Optional;

@RestController
@CrossOrigin
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    SectorService sectorService;
    @Autowired
    PuestoService puestoService;

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping("empleados/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody EmpleadoNuevo empleado,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        if(empleadoService.existeByDNI(empleado.getDni())){
            return new ResponseEntity<>(new Mensaje("Ese empleado ya existe"), HttpStatus.BAD_REQUEST);
        }
        Empleado empleadoNuevo=new Empleado(empleado.getNombre(),
                empleado.getApellido(),empleado.getIdEmpleado(),sectorService.findByNombre(empleado.getSector()),
                puestoService.findByNombre(empleado.getPuesto()),empleado.getDni());
        empleadoService.save(empleadoNuevo);
        return new ResponseEntity<>(new Mensaje("Empleado agregado con exito"),HttpStatus.CREATED);
    }
    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoNuevo>> listarEmpleados(){
        List<Empleado> listaEmpleados = empleadoService.listarEmpleados();
        List<EmpleadoNuevo> lista=new ArrayList<>();
        listaEmpleados.forEach(empleado -> lista.add(
                new EmpleadoNuevo(empleado.getId().intValue(),empleado.getNombre(),empleado.getApellido(),empleado.getIdEmpleado(),
                        empleado.getSector().getSector(),empleado.getPuesto().getPuesto(),empleado.getDni())));
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/empleados/detail/{id}")
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
    @GetMapping("/empleados/dispositivos/{id}")
    public ResponseEntity<List<Dispositivo>> listarDispositivos(@PathVariable("id")int id){
        Optional<Empleado> empleado=empleadoService.getOne((long)id);
        List<Dispositivo> dispositivos= empleado.get().getDispositivos();
        List<DispositivoNuevo> dispositivoNuevos=new ArrayList<>();
        dispositivos.forEach(dispositivo -> dispositivoNuevos.add(crearDispositivoNuevo(dispositivo)));
        return new ResponseEntity(dispositivoNuevos, HttpStatus.OK);
    }
    public DispositivoNuevo crearDispositivoNuevo(Dispositivo dispositivo){
        if (dispositivo.getEmpleadoActual()==null){
            return new DispositivoNuevo(dispositivo.getId(),dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),
                    dispositivo.getModelo(),dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(), dispositivo.getValor(), dispositivo.getAsegurado(), (long) 0);
        }
        return new DispositivoNuevo(dispositivo.getId(),dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),
                dispositivo.getModelo(),dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(), dispositivo.getValor(), dispositivo.getAsegurado(),dispositivo.getEmpleadoActual().getId());
    }
    @PostMapping("/empleados/dispositivos/asignar/{id}")
    public ResponseEntity<?> asignarDispositivos(@PathVariable("id")int id, @RequestBody int idDispositivos){
        if(!empleadoService.existsById((long)id))
            return new ResponseEntity(new Mensaje("no existe empleado"), HttpStatus.NOT_FOUND);
        //if(!dispositivoService.existsById((long) id))
        //  return new ResponseEntity(new Mensaje("no existe dispositivo con id " + idDispositivos), HttpStatus.NOT_FOUND);

        Empleado empleado = empleadoService.getOne((long)id).get();
        List<Dispositivo> dispositivos=empleado.getDispositivos();
        Dispositivo dispositivo=dispositivoService.findDispositivo((long)idDispositivos);
        dispositivo.setEmpleadoActual(empleado);
        dispositivos.add(dispositivo);
        empleado.setDispositivos(dispositivos);
        empleadoService.save(empleado);
        dispositivoService.save(dispositivo);
        return new ResponseEntity(new Mensaje("Dispositivo agregado"), HttpStatus.OK);
    }
    @PostMapping("/empleados/dispositivos/quitar/{id}")
    public ResponseEntity<?> quitarDispositivo(@PathVariable("id")int id, @RequestBody int idDispositivos){
        if(!empleadoService.existsById((long)id))
            return new ResponseEntity(new Mensaje("no existe empleado"), HttpStatus.NOT_FOUND);
        Empleado empleado = empleadoService.getOne((long)id).get();
        List<Dispositivo> dispositivos= empleado.getDispositivos();
        dispositivos.remove(dispositivoService.findDispositivo((long)idDispositivos));
        empleado.setDispositivos(dispositivos);
        empleadoService.save(empleado);
        return new ResponseEntity(new Mensaje("Dispositivo quitado"), HttpStatus.OK);
    }




}
