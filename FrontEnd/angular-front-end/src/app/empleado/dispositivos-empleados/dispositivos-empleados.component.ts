import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {EmpleadoService} from '../../service/empleado.service';
import {Empleado} from '../../models/empleado';
import {Dispositivo} from '../../models/Dispositivo';
import {TokenService} from '../../service/token.service';

@Component({
  selector: 'app-dispositivos-empleados',
  templateUrl: './dispositivos-empleados.component.html',
  styleUrls: ['./dispositivos-empleados.component.css']
})
export class DispositivosEmpleadosComponent implements OnInit {
  empleado: Empleado = new Empleado('','','','','',0);
  dispositivos: Dispositivo []=[];
  id: number=-1;
  roles: string []=[];
  permitido = false;
  ejecutor: string='';
  constructor(private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              private empleadoService: EmpleadoService,
              private tokenService: TokenService) { }

  ngOnInit() {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN' || rol === 'EDITOR') {
        this.permitido = true;
      }
    });
    if (this.tokenService.getToken()) {
      this.ejecutor = this.tokenService.getUserName();
    }
    this.id = this.activatedRoute.snapshot.params['id'];
    this.empleadoService.detail(this.id).subscribe(
      data => {
        this.empleado = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
    this.listarDispositivosEmpleados();
  }
  listarDispositivosEmpleados(): void {
    this.empleadoService.listarDispositivos(this.id).subscribe(data => {
        this.dispositivos = data;
      },
      error => {
        console.log(error);
      });
  }
  quitar(idDisp: number): void {
    this.empleadoService.quitarDispositivo(this.id, idDisp, this.ejecutor).subscribe(
      data => {
        this.toastr.success('Dispositivo quitado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        window.location.reload();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
