package com.example.demo.services.dispositivo;

import com.example.demo.models.historial.HistorialDispositivo;
import com.example.demo.repositories.dispositivo.HistorialDispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistorialDispositivoService {
    @Autowired
    HistorialDispositivoRepository historialDispositivoRepository;

    public HistorialDispositivo findByID(long id){
        return historialDispositivoRepository.findById(id).get();
    }
    public void save(HistorialDispositivo historialDispositivo){
        this.historialDispositivoRepository.save(historialDispositivo);
    }
    public List<HistorialDispositivo> listarHistorial(){
        return historialDispositivoRepository.findAll();
    }
}
