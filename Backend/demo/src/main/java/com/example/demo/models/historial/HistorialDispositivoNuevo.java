package com.example.demo.models.historial;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistorialDispositivoNuevo {
    Long idEmpleado;
    LocalDate fechaAsignacion;
    LocalDate fechaDesincronizacion;

    public HistorialDispositivoNuevo(Long idEmpleado, LocalDate fechaAsignacion, LocalDate fechaDesincronizacion) {
        this.idEmpleado = idEmpleado;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDesincronizacion = fechaDesincronizacion;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDate getFechaDesincronizacion() {
        return fechaDesincronizacion;
    }

    public void setFechaDesincronizacion(LocalDate fechaDesincronizacion) {
        this.fechaDesincronizacion = fechaDesincronizacion;
    }
}
