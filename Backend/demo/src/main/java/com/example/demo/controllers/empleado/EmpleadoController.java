package com.example.demo.controllers.empleado;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.EmpleadoNuevo;
import com.example.demo.models.Mensaje;
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

@RestController
@CrossOrigin
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    SectorService sectorService;
    @Autowired
    PuestoService puestoService;

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

}
