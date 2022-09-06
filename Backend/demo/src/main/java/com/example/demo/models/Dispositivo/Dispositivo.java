package com.example.demo.models.Dispositivo;

import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Dispositivo")
public class Dispositivo extends EntidadPersistente {
  @ManyToOne
  TipoDispositivoModel tipo;
  String numeroDeSerie;
  String modelo;
  String idDispo;
  @ManyToOne
  MarcaModel marca;
  Float valor;
  boolean asegurado;
  @OneToOne
  Empleado empleadoActual;

  public Dispositivo(TipoDispositivoModel tipo, String numeroDeSerie, String modelo,String idDispo, MarcaModel marca, Float valor, boolean asegurado,Empleado empleadoActual) {
    this.tipo = tipo;
    this.numeroDeSerie = numeroDeSerie;
    this.modelo = modelo;
    this.idDispo=idDispo;
    this.marca = marca;
    this.valor = valor;
    this.asegurado = asegurado;
    this.empleadoActual = empleadoActual;
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

  public String getIdDispo() {
    return idDispo;
  }

  public void setIdDispo(String idDispo) {
    this.idDispo = idDispo;
  }

  public Empleado getEmpleadoActual() {
    return empleadoActual;
  }

  public void setEmpleadoActual(Empleado empleado) {
    this.empleadoActual = empleado;
  }
}
