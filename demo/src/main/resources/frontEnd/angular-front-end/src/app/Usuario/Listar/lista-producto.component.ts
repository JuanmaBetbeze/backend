import { Component, OnInit } from '@angular/core';
import { Producto } from '../../models/producto';
import { UsuarioService } from '../../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../../service/token.service';
import {Usuario} from '../../models/Usuario';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit {

  usuarios: Usuario[] = [];
  roles: string[]=[];
  isAdmin = false;

  constructor(
    private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarUsuarios();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  cargarUsuarios(): void {
    this.usuarioService.listarUsuarios().subscribe(
      data => {
        this.usuarios = data;

      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(nombreUsuario: string) {
    this.usuarioService.delete(nombreUsuario).subscribe(
      data => {
        this.toastr.success('Producto Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarUsuarios();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
  usuarioNotAdmin(usuario: Usuario): boolean {
    if (usuario.roles.includes('Admin')) {
      return false;
    }
    return true;
  }

}
