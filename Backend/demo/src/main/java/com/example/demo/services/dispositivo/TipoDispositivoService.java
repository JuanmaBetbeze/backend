package com.example.demo.services.dispositivo;

import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.repositories.dispositivo.TipoDispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoDispositivoService {
    @Autowired
    TipoDispositivoRepository tipoDispositivoRepository;

    public boolean existe(String tipo){
        return tipoDispositivoRepository.existsByTipo(tipo);
    }
    public void save(TipoDispositivoModel tipo){
        tipoDispositivoRepository.save(tipo);
    }
    public List<TipoDispositivoModel> listarTipos(){
        return tipoDispositivoRepository.findAll();
    }
    public TipoDispositivoModel findByNombre(String tipo){
        return tipoDispositivoRepository.findByTipo(tipo);
    }
}
