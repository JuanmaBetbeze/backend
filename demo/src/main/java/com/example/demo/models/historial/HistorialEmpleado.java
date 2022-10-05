package com.example.demo.models.historial;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "historialEmpleado")
public class HistorialEmpleado extends EntidadPersistente {
    @OneToOne
    Dispositivo dispositivo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    LocalDate fechaAsignacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    LocalDate fechaDesincronizacion;

    String ejecutor;

    public HistorialEmpleado() {
    }

    public HistorialEmpleado(Dispositivo dispositivo, LocalDate fechaAsignacion, LocalDate fechaDesincronizacion, String ejecutor) {
        this.dispositivo = dispositivo;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDesincronizacion = fechaDesincronizacion;
        this.ejecutor = ejecutor;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
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

    public String getEjecutor() {
        return ejecutor;
    }

    public void setEjecutor(String ejecutor) {
        this.ejecutor = ejecutor;
    }
}
