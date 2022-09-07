package com.example.demo.models.historial;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "historialDispositivo")
public class HistorialDispositivo extends EntidadPersistente {
    @OneToOne
    Empleado empleado;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    LocalDate fechaAsignacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    LocalDate fechaDesincronizacion;

    public HistorialDispositivo(Empleado empleado, LocalDate fechaAsignacion, LocalDate fechaDesincronizacion) {
        this.empleado = empleado;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDesincronizacion = fechaDesincronizacion;
    }

    public HistorialDispositivo() {
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }


    public void setFechaDesincronizacion(LocalDate fechaDesincronizacion) {
        this.fechaDesincronizacion = fechaDesincronizacion;
    }

    public LocalDate getFechaDesincronizacion() {
        return fechaDesincronizacion;
    }
}
