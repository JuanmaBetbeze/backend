package com.example.demo.services.dispositivo;

import com.example.demo.models.Dispositivo.MarcaModel;
import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.repositories.dispositivo.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MarcaService {
    @Autowired
    MarcaRepository marcaRepository;

    public boolean existe(String marca){
        return marcaRepository.existsByMarca(marca);
    }
    public void save(MarcaModel marca){
        marcaRepository.save(marca);
    }
    public List<MarcaModel> listarMarcas(){
        return marcaRepository.findAll();
    }
    public MarcaModel findByNombre(String marca){
        return marcaRepository.findByMarca(marca);
    }
}
