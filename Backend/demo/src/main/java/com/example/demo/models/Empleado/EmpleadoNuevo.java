package com.example.demo.models.Empleado;

public class EmpleadoNuevo {
    int id;
    String nombre;
    String apellido;
    String idEmpleado;
    String sector;
    String puesto;
    int dni;

    public EmpleadoNuevo(int id,String nombre, String apellido, String idEmpleado, String sector, String puesto, int dni) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idEmpleado = idEmpleado;
        this.sector = sector;
        this.puesto = puesto;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
