package com.example.demo.controllers.dispositivo;

import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Mensaje;
import com.example.demo.services.dispositivo.TipoDispositivoService;
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
public class TipoDispositivoController {
    @Autowired
    TipoDispositivoService tipoDispositivoService;

    @PostMapping("tipoDispositivo/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody String tipoDispositivo,
                                   BindingResult bindingResult){
        if(tipoDispositivoService.existe(tipoDispositivo)) {
            return new ResponseEntity<>(new Mensaje("Ese Tipo ya existe"), HttpStatus.BAD_REQUEST);
        }
        TipoDispositivoModel tipo=new TipoDispositivoModel();
        tipo.setTipo(tipoDispositivo);
        tipoDispositivoService.save(tipo);
        return new ResponseEntity<>(new Mensaje("Tipo agregado con exito"),HttpStatus.CREATED);

    }
    @GetMapping("tipoDispositivo")
    public ResponseEntity<List<String>> listarTipos(){
        List<TipoDispositivoModel> lista=tipoDispositivoService.listarTipos();
        List<String> listaString=new ArrayList<>();
        lista.forEach(tipoDispositivoModel -> listaString.add(tipoDispositivoModel.getTipo()));
        return new ResponseEntity(listaString, HttpStatus.OK);
    }

}
