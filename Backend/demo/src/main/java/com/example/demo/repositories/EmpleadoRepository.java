package com.example.demo.repositories;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Empleado.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    boolean existsByDni(int dni);
    boolean existsByNombre(String nombre);
    Optional<Empleado> getEmpleadoByNombre(String nombre);
}
