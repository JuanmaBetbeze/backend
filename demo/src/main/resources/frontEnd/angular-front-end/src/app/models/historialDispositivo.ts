export class HistorialDispositivo {
  id?: number;
  nombre: string;
  apellido: string;
  idEmpleado: string;
  sector: string;
  puesto: string;
  dni: number;
  fechaAsignacion: Date;
  fechaDesincronizacion: Date;
  ejecutor: string;

  constructor(nombre: string, apellido: string, idEmpleado: string, sector: string, puesto: string, dni: number, fechaAsignacion: Date, fechaDesincronizacion: Date, ejecutor: string) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.idEmpleado = idEmpleado;
    this.sector = sector;
    this.puesto = puesto;
    this.dni = dni;
    this.fechaAsignacion = fechaAsignacion;
    this.fechaDesincronizacion = fechaDesincronizacion;
    this.ejecutor = ejecutor;
  }
}
