package com.example.demo.services.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Dispositivo.DispositivoNuevo;
import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Empleado.Empleado;
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
    public Dispositivo findDispositivo(Long id){
        return dispositivoRepository.findById(id).get();
    }

    public boolean existsById(Long id) {
        return dispositivoRepository.existsById(id);
    }
    public List<Dispositivo> findByAsegurado(boolean asegurado){
        return dispositivoRepository.findDispositivosByAsegurado(asegurado);
    }
    public void eliminarDispositivo(Long id){
        dispositivoRepository.deleteById(id);
    }
    public Dispositivo findDispositivoByModelo(String modelo){
        return dispositivoRepository.findDispositivoByModelo(modelo);
    }
    public List<Dispositivo> findByEmpleadoActual(Empleado empleado){
        return dispositivoRepository.findDispositivosByEmpleadoActual(empleado);
    }
    public List<Dispositivo> findByTipo(TipoDispositivoModel tipo){
        return dispositivoRepository.findDispositivosByTipo(tipo);
    }
}
