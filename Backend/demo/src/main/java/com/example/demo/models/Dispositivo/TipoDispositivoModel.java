package com.example.demo.models.Dispositivo;

import com.example.demo.models.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TypeOfDevice")
public class TipoDispositivoModel extends EntidadPersistente {
  String tipo;

  public String getTipo () {
    return tipo;
  }

  public void setTipo (String tipo) {
    this.tipo = tipo;
  }
}
