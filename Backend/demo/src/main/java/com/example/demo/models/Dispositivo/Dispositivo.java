package com.example.demo.models.Dispositivo;

import com.example.demo.models.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Device")
public class Dispositivo extends EntidadPersistente {
  @ManyToOne
  TipoDispositivoModel tipo;
  String numeroDeSerie;
  String modelo;
  @ManyToOne
  MarcaModel marca;
  Float valor;
  boolean asegurado;

  public Dispositivo(TipoDispositivoModel tipo, String numeroDeSerie, String modelo, MarcaModel marca, Float valor, boolean asegurado) {
    this.tipo = tipo;
    this.numeroDeSerie = numeroDeSerie;
    this.modelo = modelo;
    this.marca = marca;
    this.valor = valor;
    this.asegurado = asegurado;
  }

  public Dispositivo() {
  }


  public TipoDispositivoModel getTipo () {
    return tipo;
  }

  public void setTipo (TipoDispositivoModel tipo) {
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

  public MarcaModel getMarca () {
    return marca;
  }

  public void setMarca (MarcaModel marca) {
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
