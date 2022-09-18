package com.example.demo.models.historial;

import java.time.LocalDate;

public class HistorialEmpleadoNuevo {
    Long idDispo;
    LocalDate fechaAsignacion;
    LocalDate fechaDesincronizacion;
    String ejecutor;

    public HistorialEmpleadoNuevo(Long idDispo, LocalDate fechaAsignacion, LocalDate fechaDesincronizacion, String ejecutor) {
        this.idDispo = idDispo;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDesincronizacion = fechaDesincronizacion;
        this.ejecutor = ejecutor;
    }

    public Long getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(Long idDispo) {
        this.idDispo = idDispo;
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
