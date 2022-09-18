package com.example.demo.models.Dispositivo;


import com.example.demo.models.Empleado.Empleado;

public class DispositivoNuevo {
    Long id;
    String tipo;
    String numeroDeSerie;
    String modelo;
    String idDispo;
    String marca;
    Float valor;
    boolean asegurado;
    Long empleadoActual;
    String ejecutor;
    int estado;

    public DispositivoNuevo(Long id,String tipo, String numeroDeSerie, String modelo, String idDispo,String marca,
                            Float valor, boolean asegurado,Long empleadoActual,String ejecutor,int estado) {
        this.id=id;
        this.tipo = tipo;
        this.numeroDeSerie = numeroDeSerie;
        this.modelo = modelo;
        this.idDispo=idDispo;
        this.marca = marca;
        this.valor = valor;
        this.asegurado = asegurado;
        this.empleadoActual=empleadoActual;
        this.ejecutor=ejecutor;
        this.estado=estado;
    }


    public String getTipo () {
        return tipo;
    }

    public void setTipo (String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroDeSerie () {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie (String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getModelo () {
        return modelo;
    }

    public void setModelo (String modelo) {
        this.modelo = modelo;
    }

    public String getMarca () {
        return marca;
    }

    public void setMarca (String marca) {
        this.marca = marca;
    }

    public Float getValor () {
        return valor;
    }

    public void setValor (Float valor) {
        this.valor = valor;
    }

    public boolean getAsegurado () {
        return asegurado;
    }

    public void setAsegurado (boolean asegurado) {
        this.asegurado = asegurado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAsegurado() {
        return asegurado;
    }

    public String getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(String idDispo) {
        this.idDispo = idDispo;
    }


    public Long getEmpleadoActual() {
        return empleadoActual;
    }

    public void setEmpleadoActual(Long empleadoActual) {
        this.empleadoActual = empleadoActual;
    }

    public String getEjecutor() {
        return ejecutor;
    }

    public void setEjecutor(String ejecutor) {
        this.ejecutor = ejecutor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
