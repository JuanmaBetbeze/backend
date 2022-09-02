package com.example.demo.services.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.repositories.dispositivo.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DispositivoService {
    @Autowired
    DispositivoRepository dispositivoRepository;

    public boolean existeByModelo(String modelo){
        return dispositivoRepository.existsByModelo(modelo);
    }
    public void save(Dispositivo dispositivo){
        dispositivoRepository.save(dispositivo);
    }
    public List<Dispositivo> listarDispositivos(){
        return dispositivoRepository.findAll();
    }
}
