package com.example.demo.services.empleado;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Empleado.SectorModel;
import com.example.demo.repositories.empleado.EmpleadoRepository;
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

    public void eliminarEmpleado(Long id){
        empleadoRepository.deleteById(id);
    }
    public List<Empleado> findByNombre(String nombre){
        return empleadoRepository.findEmpleadosByNombre(nombre);
    }
    public List<Empleado> findByApellido(String apellido){
        return empleadoRepository.findEmpleadosByApellido(apellido);
    }
    public List<Empleado> findByDni(int dni){
        return empleadoRepository.findEmpleadosByDni(dni);
    }
    public List<Empleado> findByIdEmpleado(String idEmpleado){
        return empleadoRepository.findEmpleadosByIdEmpleado(idEmpleado);
    }
    public List<Empleado> findByPuesto(PuestoModel puesto){
        return empleadoRepository.findEmpleadosByPuesto(puesto);
    }
    public List<Empleado> findBySector(SectorModel sectorModel){
        return empleadoRepository.findEmpleadosBySector(sectorModel);
    }


}
