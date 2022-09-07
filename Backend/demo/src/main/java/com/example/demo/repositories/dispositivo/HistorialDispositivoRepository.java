package com.example.demo.repositories.dispositivo;

import com.example.demo.models.historial.HistorialDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialDispositivoRepository extends JpaRepository<HistorialDispositivo, Long> {
    List<HistorialDispositivo> getHistorialDispositivoByIdOrderByFechaDesincronizacion(Long id);
}
