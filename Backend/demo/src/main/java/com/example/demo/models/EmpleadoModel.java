package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class EmpleadoModel extends EntidadPersistente {
  public String nombre;
  public String apellido;
  public int idEmpleado;
  @ManyToOne
  public SectorModel sector;
  @ManyToOne
  public PuestoModel puesto;
  public int dni;

  public String getNombre () {
    return nombre;
  }

  public void setNombre (String nombre) {
    this.nombre = nombre;
  }

  public String getApellido () {
    return apellido;
  }

  public void setApellido (String apellido) {
    this.apellido = apellido;
  }

  public SectorModel getSector () {
    return sector;
  }

  public void setSector (SectorModel sector) {
    this.sector = sector;
  }

  public PuestoModel getPuesto () {
    return puesto;
  }

  public void setPuesto (PuestoModel puesto) {
    this.puesto = puesto;
  }

  public int getDni () {
    return dni;
  }

  public void setDni (int dni) {
    this.dni = dni;
  }

  public int getIdEmpleado () {
    return idEmpleado;
  }

  public void setIdEmpleado (int idEmpleado) {
    this.idEmpleado = idEmpleado;
  }
}
