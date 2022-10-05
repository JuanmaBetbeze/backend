package com.example.demo.repositories.empleado;

import com.example.demo.models.historial.HistorialEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialEmpleadoRepository extends JpaRepository<HistorialEmpleado, Long> {
    List<HistorialEmpleado> getHistorialEmpleadosByIdOrderByFechaDesincronizacion(Long id);
}
