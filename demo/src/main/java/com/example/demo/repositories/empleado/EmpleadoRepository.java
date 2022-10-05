package com.example.demo.repositories.empleado;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Empleado.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    boolean existsByDni(int dni);
    boolean existsByNombre(String nombre);
    Optional<Empleado> getEmpleadoByNombre(String nombre);
    List<Empleado> findEmpleadosByNombre(String nombre);
    List<Empleado> findEmpleadosByApellido(String apellido);
    List<Empleado> findEmpleadosByIdEmpleado(String idEmpleado);
    List<Empleado> findEmpleadosBySector(SectorModel sectorModel);
    List<Empleado> findEmpleadosByPuesto(PuestoModel puesto);
    List<Empleado> findEmpleadosByDni(int dni);

}
