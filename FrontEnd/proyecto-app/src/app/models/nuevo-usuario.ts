export class NuevoUsuario {
    nombreUsuario: string;
    password: string;
    roles: string[];
    constructor(nombreUsuario: string, password: string, roles: string[]) {

        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.roles = roles;
    }
}
