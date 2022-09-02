package com.example.demo.models.Dispositivo;


public class DispositivoNuevo {
    String tipo;
    String numeroDeSerie;
    String modelo;
    String marca;
    Float valor;
    boolean asegurado;

    public DispositivoNuevo(String tipo, String numeroDeSerie, String modelo, String marca, Float valor, boolean asegurado) {
        this.tipo = tipo;
        this.numeroDeSerie = numeroDeSerie;
        this.modelo = modelo;
        this.marca = marca;
        this.valor = valor;
        this.asegurado = asegurado;
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
}
