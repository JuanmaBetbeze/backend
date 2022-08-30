package com.example.demo.services;

import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Empleado.SectorModel;
import com.example.demo.repositories.PuestoRepository;
import com.example.demo.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PuestoService {
    @Autowired
    PuestoRepository puestoRepository;

    public void save(PuestoModel puestoModel){
        puestoRepository.save(puestoModel);
    }
    public boolean existe(String puesto){
        return puestoRepository.existsByPuesto(puesto);
    }
    public List<PuestoModel> listarPuestos(){
        return puestoRepository.findAll();
    }
}
