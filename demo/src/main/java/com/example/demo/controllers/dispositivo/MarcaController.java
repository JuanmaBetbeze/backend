package com.example.demo.controllers.dispositivo;

import com.example.demo.models.Dispositivo.MarcaModel;
import com.example.demo.models.Mensaje;
import com.example.demo.services.dispositivo.MarcaService;
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
public class MarcaController {
    @Autowired
    MarcaService marcaService;

    @PostMapping("marca/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody String marca,
                                   BindingResult bindingResult){
        if(marcaService.existe(marca)) {
            return new ResponseEntity<>(new Mensaje("Esa marca ya existe"), HttpStatus.BAD_REQUEST);
        }
        MarcaModel marcaModel=new MarcaModel();
        marcaModel.setMarca(marca);
        marcaService.save(marcaModel);
        return new ResponseEntity<>(new Mensaje("Marca agregada con exito"),HttpStatus.CREATED);

    }
    @GetMapping("marca")
    public ResponseEntity<List<String>> listarMarcas(){
        List<MarcaModel> lista=marcaService.listarMarcas();
        List<String> listaString=new ArrayList<>();
        lista.forEach(marcaModel -> listaString.add(marcaModel.getMarca()));
        return new ResponseEntity(listaString, HttpStatus.OK);
    }
}
