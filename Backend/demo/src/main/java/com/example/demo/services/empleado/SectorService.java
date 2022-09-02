package com.example.demo.services.empleado;

import com.example.demo.models.Empleado.SectorModel;
import com.example.demo.repositories.empleado.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SectorService {
    @Autowired
    SectorRepository sectorRepository;

    public void save(SectorModel sectorModel){
        sectorRepository.save(sectorModel);
    }
    public boolean existe(String sector){
        return sectorRepository.existsBySector(sector);
    }
    public List<SectorModel> listarSectores(){
        return sectorRepository.findAll();
    }
    public SectorModel findByNombre(String sector){
        return sectorRepository.findBySector(sector);
    }
}
