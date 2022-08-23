package com.example.demo.models.Dispositivo;

import com.example.demo.models.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Brand")
public class MarcaModel extends EntidadPersistente {
  String marca;

  public String getMarca () {
    return marca;
  }

  public void setMarca (String marca) {
    this.marca = marca;
  }
}
