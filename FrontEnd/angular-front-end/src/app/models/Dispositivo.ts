export class Dispositivo {
  id: number;
  tipo: string;
  numeroDeSerie: string;
  modelo: string;
  idDispo: string;
  marca: string;
  valor: number;
  asegurado: boolean;
  empleadoActual?: number;
  agregar: boolean;
  ejecutor: string;
  estado: number;

  constructor(tipo: string, numeroDeSerie: string, modelo: string, idDispo: string, marca: string, valor: number,
              asegurado: boolean, empleadoActual: number, agregar: boolean, ejecutor: string, estado: number) {
    this.tipo = tipo;
    this.numeroDeSerie = numeroDeSerie;
    this.modelo = modelo;
    this.idDispo = idDispo;
    this.marca = marca;
    this.valor = valor;
    this.asegurado = asegurado;
    this.empleadoActual = empleadoActual;
    this.agregar = agregar;
    this.ejecutor = ejecutor;
    this.id=0;
    this.estado=estado;
  }
}
