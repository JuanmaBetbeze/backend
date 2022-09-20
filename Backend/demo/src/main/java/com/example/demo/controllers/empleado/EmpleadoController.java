package com.example.demo.controllers.empleado;

import com.example.demo.enums.EstadoDispositivo;
import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.models.Dispositivo.MarcaModel;
import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.EmpleadoNuevo;
import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Empleado.SectorModel;
import com.example.demo.models.Mensaje;
import com.example.demo.models.historial.HistorialDispositivo;
import com.example.demo.models.historial.HistorialEmpleado;
import com.example.demo.models.historial.HistorialEmpleadoNuevo;
import com.example.demo.services.dispositivo.DispositivoService;
import com.example.demo.services.dispositivo.HistorialDispositivoService;
import com.example.demo.services.empleado.EmpleadoService;
import com.example.demo.services.empleado.HistorialEmpleadoService;
import com.example.demo.services.empleado.PuestoService;
import com.example.demo.services.empleado.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    HistorialDispositivoService historialDispositivoService;

    @Autowired
    HistorialEmpleadoService historialEmpleadoService;

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
        List<HistorialDispositivo> historialDispositivos=historialDispositivoService.listarHistorial();
        historialDispositivos.forEach(historialDispositivo -> this.eliminarHistorial(historialDispositivo,id));
        Empleado empleado= empleadoService.getOne((long)id).get();
        List<HistorialEmpleado> historialEmpleados=empleado.getHistorialEmpleados();
        if(!historialEmpleados.isEmpty()) {
            empleado.setHistorialEmpleados(null);
            empleadoService.save(empleado);
            historialEmpleados.forEach(historialEmpleado ->historialEmpleadoService.eliminar(historialEmpleado.getId()));
        }
        List<Dispositivo>dispositivos=dispositivoService.findByDeshabilitado(false);
        List<Dispositivo>disposotivosNotNull=dispositivos.stream().filter(dispositivo -> dispositivo.getEmpleadoActual()!=null).collect(Collectors.toList());
        disposotivosNotNull.stream().filter(dispositivo -> dispositivo.getEmpleadoActual().getId()==id).forEach(this::setearEmpleadoActual);
        empleado.setDispositivos(null);
        empleadoService.eliminarEmpleado((long)id);
        return new ResponseEntity<>(new Mensaje("Empleado eliminado con exito"),HttpStatus.CREATED);

    }
    public void setearEmpleadoActual(Dispositivo dispositivo){
        dispositivo.setEmpleadoActual(null);
        dispositivo.setEstadoDispositivo(EstadoDispositivo.SINASIGNAR);
        dispositivoService.save(dispositivo);
    }
    public void eliminarHistorial(HistorialDispositivo historialDispositivo,int id){
        List<Dispositivo> dispositivos=dispositivoService.findByDeshabilitado(false);
        if(historialDispositivo.getEmpleado().getId().intValue()==id) {
            dispositivos.forEach(dispositivo->quitarHistorial(dispositivo,historialDispositivo));
            historialDispositivoService.eliminar(historialDispositivo.getId());
        }
    }
    public void quitarHistorial(Dispositivo dispositivo,HistorialDispositivo historialDispositivo){
        List<HistorialDispositivo>historialDispositivos=dispositivo.getHistorialDispositivo();
        if(historialDispositivos.contains(historialDispositivo)){
            historialDispositivos.remove(historialDispositivo);
            dispositivo.setHistorialDispositivo(historialDispositivos);
            dispositivoService.save(dispositivo);
        }
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
                    dispositivo.getModelo(),dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(), dispositivo.getValor(), dispositivo.getAsegurado(), (long) 0,
                    dispositivo.getEjecutor(),dispositivo.getEstadoDispositivo().ordinal(),dispositivo.getMotivo());
        }
        return new DispositivoNuevo(dispositivo.getId(),dispositivo.getTipo().getTipo(),dispositivo.getNumeroDeSerie(),
                dispositivo.getModelo(),dispositivo.getIdDispo(),dispositivo.getMarca().getMarca(), dispositivo.getValor(),
                dispositivo.getAsegurado(),dispositivo.getEmpleadoActual().getId(),dispositivo.getEjecutor(),dispositivo.getEstadoDispositivo().ordinal(),
                dispositivo.getMotivo());
    }
    @PostMapping("/empleados/dispositivos/asignar/{id}/{ejecutor}")
    public ResponseEntity<?> asignarDispositivos(@PathVariable("id")int id,@PathVariable("ejecutor")String ejecutor, @RequestBody int idDispositivos){
        if(!empleadoService.existsById((long)id))
            return new ResponseEntity(new Mensaje("no existe empleado"), HttpStatus.NOT_FOUND);
        //if(!dispositivoService.existsById((long) id))
        //  return new ResponseEntity(new Mensaje("no existe dispositivo con id " + idDispositivos), HttpStatus.NOT_FOUND);

        Empleado empleado = empleadoService.getOne((long)id).get();
        List<Dispositivo> dispositivos=empleado.getDispositivos();
        Dispositivo dispositivo=dispositivoService.findDispositivo((long)idDispositivos);
        List<HistorialDispositivo> historialDispositivos=dispositivo.getHistorialDispositivo();
        List<HistorialEmpleado> historialEmpleados=empleado.getHistorialEmpleados();
        HistorialDispositivo nuevo=new HistorialDispositivo(empleado, LocalDate.now(),null,null);
        historialDispositivoService.save(nuevo);
        historialDispositivos.add(nuevo);
        HistorialEmpleado historialEmpleado=new HistorialEmpleado(dispositivo,LocalDate.now(),null,null);
        historialEmpleadoService.save(historialEmpleado);

        historialEmpleados.add(historialEmpleado);
        dispositivo.setHistorialDispositivo(historialDispositivos);
        empleado.setHistorialEmpleados(historialEmpleados);
        dispositivo.setEmpleadoActual(empleado);
        dispositivo.setEstadoDispositivo(EstadoDispositivo.ASIGNADO);
        dispositivo.setEjecutor(ejecutor);
        dispositivos.add(dispositivo);
        empleado.setDispositivos(dispositivos);
        empleadoService.save(empleado);
        dispositivoService.save(dispositivo);
        return new ResponseEntity(new Mensaje("Dispositivo agregado"), HttpStatus.OK);
    }
    @PostMapping("/empleados/dispositivos/quitar/{id}/{ejecutor}")
    public ResponseEntity<?> quitarDispositivo(@PathVariable("id")int id,@PathVariable("ejecutor")String ejecutor, @RequestBody int idDispositivos){
        if(!empleadoService.existsById((long)id))
            return new ResponseEntity(new Mensaje("no existe empleado"), HttpStatus.NOT_FOUND);
        Empleado empleado = empleadoService.getOne((long)id).get();
        List<Dispositivo> dispositivos= empleado.getDispositivos();
        Dispositivo dispositivo=dispositivoService.findDispositivo((long)idDispositivos);
        dispositivos.remove(dispositivo);
        dispositivo.setEmpleadoActual(null);
        dispositivo.setEstadoDispositivo(EstadoDispositivo.SINASIGNAR);
        List<HistorialDispositivo> historialDispositivo=dispositivo.getHistorialDispositivo();
        List<HistorialEmpleado> historialEmpleados=empleado.getHistorialEmpleados();
        historialDispositivo.forEach(historialDispositivo1 -> this.desasignar(historialDispositivo1,ejecutor));
        historialEmpleados.forEach(historialEmpleado -> this.desasignar2(historialEmpleado,ejecutor));
        empleado.setDispositivos(dispositivos);
        dispositivoService.save(dispositivo);
        empleadoService.save(empleado);
        return new ResponseEntity(new Mensaje("Dispositivo quitado"), HttpStatus.OK);
    }
    public void desasignar(HistorialDispositivo historialDispositivo,String ejecutor){
        if(historialDispositivo.getFechaDesincronizacion()==null){
            historialDispositivo.setFechaDesincronizacion(LocalDate.now());
            historialDispositivo.setEjecutor(ejecutor);
            historialDispositivoService.save(historialDispositivo);
        }
    }
    public void desasignar2(HistorialEmpleado historialEmpleado,String ejecutor){
        if(historialEmpleado.getFechaDesincronizacion()==null){
            historialEmpleado.setFechaDesincronizacion(LocalDate.now());
            historialEmpleado.setEjecutor(ejecutor);
            historialEmpleadoService.save(historialEmpleado);
        }
    }
    @PostMapping("/empleados/filtrar")
    public ResponseEntity<List<EmpleadoNuevo>> filtrar(@RequestBody List<String> filtrarList){
        String filtrado=filtrarList.get(0);
        String valor=filtrarList.get(1);
        List<Empleado>empleados=new ArrayList<>();
        if(Objects.equals(filtrado, "sector")){
            SectorModel sectorModel=sectorService.findByNombre(valor);
            empleados=empleadoService.findBySector(sectorModel);
        }
        if(Objects.equals(filtrado,"nombre")){
            empleados=empleadoService.findByNombre(valor);
        }
        if(Objects.equals(filtrado,"apellido")){
            empleados=empleadoService.findByApellido(valor);
        }
        if(Objects.equals(filtrado,"dni")){
            empleados=empleadoService.findByDni(Integer.parseInt(valor));
        }
        if(Objects.equals(filtrado,"puesto")){
            PuestoModel puestoModel=puestoService.findByNombre(valor);
            empleados=empleadoService.findByPuesto(puestoModel);
        }
        if(Objects.equals(filtrado,"idEmpleado")){
            empleados=empleadoService.findByIdEmpleado(valor);
        }
        if (Objects.equals(filtrado,"listar")){
            empleados=empleadoService.listarEmpleados();
        }
        List<EmpleadoNuevo> lista=new ArrayList<>();
        empleados.forEach(empleado -> lista.add(new EmpleadoNuevo
                (empleado.getId().intValue(),empleado.getNombre(),
                        empleado.getApellido(),empleado.getIdEmpleado(),
                        empleado.getSector().getSector(),
                empleado.getPuesto().getPuesto(),empleado.getDni())));

        return new ResponseEntity(lista, HttpStatus.OK);
    }




}
