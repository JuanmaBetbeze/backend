import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {Usuario} from '../../models/Usuario';
import {NuevoUsuario} from '../../models/nuevo-usuario';

@Component({
  selector: 'app-nuevo-producto',
  templateUrl: './nuevo-producto.component.html',
  styleUrls: ['./nuevo-producto.component.css']
})
export class NuevoProductoComponent implements OnInit {
  nombreUsuario: string;
  password: string;
  usuario: NuevoUsuario;
  roles: string [] = [];
  rol: string;
  rolNotNull = false;
  constructor(
    private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onCreate(): void {

    this.roles.push(this.rol);
    this.usuario = new NuevoUsuario(this.nombreUsuario, this.password, this.roles);

    this.usuarioService.save(this.usuario).subscribe(
      data => {
        this.toastr.success('Usuario Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/usuarios']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }

}
