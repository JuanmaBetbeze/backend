package com.example.demo.models.Empleado;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.EntidadPersistente;
import com.example.demo.models.historial.HistorialDispositivo;
import com.example.demo.models.historial.HistorialEmpleado;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleado")
public class Empleado extends EntidadPersistente {
  public String nombre;
  public String apellido;
  public String idEmpleado;
  @ManyToOne
  public SectorModel sector;
  @ManyToOne
  public PuestoModel puesto;
  public int dni;
  @OneToMany
  @JoinTable(name = "empleado_dispositivos",
          joinColumns = @JoinColumn(name = "empleado_id")
          , inverseJoinColumns = @JoinColumn(name = "dispositivo_id")
  )
  public List<Dispositivo> dispositivos=new ArrayList<>();
  @OneToMany
  @JoinTable(name = "historial_Empleados",
          joinColumns = @JoinColumn(name = "empleado_id")
          , inverseJoinColumns = @JoinColumn(name = "historialEmpleado_id")
  )
  List<HistorialEmpleado> historialEmpleados= new ArrayList<>();


  public Empleado() {
  }

  public Empleado(String nombre, String apellido, String idEmpleado, SectorModel sector, PuestoModel puesto, int dni) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.idEmpleado = idEmpleado;
    this.sector = sector;
    this.puesto = puesto;
    this.dni = dni;
  }

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

  public String getIdEmpleado () {
    return idEmpleado;
  }

  public void setIdEmpleado (String idEmpleado) {
    this.idEmpleado = idEmpleado;
  }

  public List<Dispositivo> getDispositivos() {
    return dispositivos;
  }

  public void setDispositivos(List<Dispositivo> dispositivos) {
    this.dispositivos = dispositivos;
  }

  public List<HistorialEmpleado> getHistorialEmpleados() {
    return historialEmpleados;
  }

  public void setHistorialEmpleados(List<HistorialEmpleado> historialEmpleados) {
    this.historialEmpleados = historialEmpleados;
  }
}
