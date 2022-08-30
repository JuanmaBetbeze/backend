export class Usuario {
  id?: number;
  nombreUsuario: string;
  password: string;
  roles: string[];
  constructor(nombreUsuario: string, password: string) {

    this.nombreUsuario = nombreUsuario;
    this.password = password;
  }
}
