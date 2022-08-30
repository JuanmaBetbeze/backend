package com.example.demo.controllers;

import com.example.demo.models.Empleado.SectorModel;
import com.example.demo.models.Mensaje;
import com.example.demo.services.SectorService;
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
public class SectorController {
    @Autowired
    SectorService sectorService;
    @PostMapping("sector/nuevo")
    public ResponseEntity<?> nuevo(@Valid
                                   @RequestBody String sectorModel,
                                   BindingResult bindingResult){
        if(sectorService.existe(sectorModel)){
            return new ResponseEntity<>(new Mensaje("Ese sector ya existe"), HttpStatus.BAD_REQUEST);
        }
        SectorModel sector=new SectorModel();
        sector.setSector(sectorModel);
        sectorService.save(sector);
        return new ResponseEntity<>(new Mensaje("Sector agregado con exito"),HttpStatus.CREATED);

    }
    @GetMapping("sector")
    public ResponseEntity<List<String>> listarSectores(){
        List<SectorModel> lista=sectorService.listarSectores();
        List<String> listaString=new ArrayList<>();
        lista.forEach(sectorModel -> listaString.add(sectorModel.getSector()));
        return new ResponseEntity(listaString, HttpStatus.OK);
    }
}
