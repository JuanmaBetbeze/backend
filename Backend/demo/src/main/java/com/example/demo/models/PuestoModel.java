package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "puesto")
public class PuestoModel extends EntidadPersistente{
  public String puesto;

  public String getPuesto () {
    return puesto;
  }

  public void setPuesto (String puesto) {
    this.puesto = puesto;
  }
}
