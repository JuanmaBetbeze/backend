package com.example.demo.models.historial;

import java.time.LocalDate;

public class HistorialDispositivoListar {
    String nombre;
    String apellido;
    String idEmpleado;
    String sector;
    String puesto;
    int dni;
    LocalDate fechaAsignacion;
    LocalDate fechaDesincronizacion;
    String ejecutor;

    public HistorialDispositivoListar(String nombre, String apellido, String idEmpleado, String sector, String puesto,
                                      int dni, LocalDate fechaAsignacion, LocalDate fechaDesincronizacion,String ejecutor) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idEmpleado = idEmpleado;
        this.sector = sector;
        this.puesto = puesto;
        this.dni = dni;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDesincronizacion = fechaDesincronizacion;
        this.ejecutor=ejecutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
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
