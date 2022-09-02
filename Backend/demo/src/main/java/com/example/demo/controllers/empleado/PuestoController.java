package com.example.demo.controllers.empleado;

import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Mensaje;
import com.example.demo.services.empleado.PuestoService;
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
public class PuestoController {
    @Autowired
    PuestoService puestoService;
    @PostMapping("puesto/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody String puesto,
                                   BindingResult bindingResult){
        if(puestoService.existe(puesto)){
            return new ResponseEntity<>(new Mensaje("Ese puesto ya existe"), HttpStatus.BAD_REQUEST);
        }
        PuestoModel puestoModel=new PuestoModel();
        puestoModel.setPuesto(puesto);
        puestoService.save(puestoModel);
        return new ResponseEntity<>(new Mensaje("Puesto agregado con exito"),HttpStatus.CREATED);

    }
    @GetMapping("puesto")
    public ResponseEntity<List<String>> listarPuestos(){
        List<PuestoModel> lista=puestoService.listarPuestos();
        List<String> listaString=new ArrayList<>();
        lista.forEach(puestoModel -> listaString.add(puestoModel.getPuesto()));
        return new ResponseEntity(listaString, HttpStatus.OK);
    }

}
