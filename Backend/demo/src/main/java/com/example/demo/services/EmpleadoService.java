package com.example.demo.services;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public void save(Empleado empleado){
        empleadoRepository.save(empleado);
    }
    public boolean existeByDNI(int dni){
        return empleadoRepository.existsByDni(dni);
    }

    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }
    public boolean existsById(Long id){
        return empleadoRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return empleadoRepository.existsByNombre(nombre);
    }
    public Optional<Empleado> getOne(Long id){
        return empleadoRepository.findById(id);
    }
    public Optional<Empleado> getByNombre(String nombre){
        return empleadoRepository.getEmpleadoByNombre(nombre);
    }
}
