package com.example.demo.services.empleado;

import com.example.demo.models.historial.HistorialDispositivo;
import com.example.demo.models.historial.HistorialEmpleado;
import com.example.demo.repositories.empleado.HistorialEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistorialEmpleadoService {
    @Autowired
    HistorialEmpleadoRepository historialEmpleadoRepository;

    public void save(HistorialEmpleado historialEmpleado){
        this.historialEmpleadoRepository.save(historialEmpleado);
    }
    public List<HistorialEmpleado> listarHistorial(){
        return historialEmpleadoRepository.findAll();
    }
    public void  eliminar(Long id){
        historialEmpleadoRepository.deleteById(id);
    }
}
