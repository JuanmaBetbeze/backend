package com.example.demo.models.Empleado;

import com.example.demo.models.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sector")
public class SectorModel extends EntidadPersistente {
  public String sector;

  public SectorModel(String sector) {
    this.sector = sector;
  }

  public SectorModel() {
  }


  public String getSector () {
    return sector;
  }

  public void setSector (String sector) {
    this.sector = sector;
  }
}
