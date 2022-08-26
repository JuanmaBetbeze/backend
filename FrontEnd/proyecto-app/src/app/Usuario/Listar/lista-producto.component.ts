import { Component, OnInit } from '@angular/core';
import { Producto } from '../../models/producto';
import { UsuarioService } from '../../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../../service/token.service';
import {NuevoUsuario} from "../../models/nuevo-usuario";

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit {

  usuarios: NuevoUsuario[] = [];
  roles: string[];
  isAdmin = false;

  constructor(
    private productoService: UsuarioService,
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
    this.productoService.listarUsuarios().subscribe(
      data => {
        this.usuarios = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: number) {
    this.productoService.delete(id).subscribe(
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

}
