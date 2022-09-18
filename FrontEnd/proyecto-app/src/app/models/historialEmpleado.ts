export class HistorialEmpleado {
  id: number;
  tipo: string;
  numeroDeSerie: string;
  modelo: string;
  idDispo: string;
  marca: string;
  valor: number;
  asegurado: boolean;
  fechaAsignacion: Date;
  fechaDesincronizacion: Date;
  ejecutor: string;

  constructor(tipo: string, numeroDeSerie: string, modelo: string, idDispo: string, marca: string, valor: number, asegurado: boolean, fechaAsignacion: Date, fechaDesincronizacion: Date, ejecutor: string) {
    this.tipo = tipo;
    this.numeroDeSerie = numeroDeSerie;
    this.modelo = modelo;
    this.idDispo = idDispo;
    this.marca = marca;
    this.valor = valor;
    this.asegurado = asegurado;
    this.fechaAsignacion = fechaAsignacion;
    this.fechaDesincronizacion = fechaDesincronizacion;
    this.ejecutor = ejecutor;
    this.id=0;
  }
}
