export class Empleado {
  id?: number;
  nombre: string;
  apellido: string;
  idEmpleado: string;
  sector: string;
  puesto: string;
  dni: number;

  constructor(nombre: string, apellido: string, idEmpleado: string, sector: string, puesto: string, dni: number) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.idEmpleado = idEmpleado;
    this.sector = sector;
    this.puesto = puesto;
    this.dni = dni;
  }



}
