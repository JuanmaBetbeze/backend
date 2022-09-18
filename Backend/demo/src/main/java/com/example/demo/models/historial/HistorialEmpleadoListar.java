package com.example.demo.models.historial;

import java.time.LocalDate;

public class HistorialEmpleadoListar {
    String tipo;
    String numeroDeSerie;
    String modelo;
    String idDispo;
    String marca;
    Float valor;
    boolean asegurado;
    LocalDate fechaAsignacion;
    LocalDate fechaDesincronizacion;
    String ejecutor;

    public HistorialEmpleadoListar(String tipo, String numeroDeSerie, String modelo, String idDispo, String marca, Float valor
            , boolean asegurado, LocalDate fechaAsignacion, LocalDate fechaDesincronizacion, String ejecutor) {
        this.tipo = tipo;
        this.numeroDeSerie = numeroDeSerie;
        this.modelo = modelo;
        this.idDispo = idDispo;
        this.marca = marca;
        this.valor = valor;
        this.asegurado = asegurado;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDesincronizacion = fechaDesincronizacion;
        this.ejecutor = ejecutor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(String idDispo) {
        this.idDispo = idDispo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public boolean isAsegurado() {
        return asegurado;
    }

    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
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
