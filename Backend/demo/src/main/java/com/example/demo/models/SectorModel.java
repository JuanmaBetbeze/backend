package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sector")
public class SectorModel extends EntidadPersistente{
  public String sector;


  public String getSector () {
    return sector;
  }

  public void setSector (String sector) {
    this.sector = sector;
  }
}
