import { Component, OnInit } from '@angular/core';
import {EmpleadoNuevo} from "../../models/EmpleadoNuevo";
import {HistorialEmpleado} from "../../models/historialEmpleado";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {EmpleadoService} from "../../service/empleado.service";
import {TokenService} from "../../service/token.service";
import {HistorialEmpleadoService} from "../../service/historialEmpleado.service";

@Component({
  selector: 'app-historial-dispositivo',
  templateUrl: './historial-dispositivo.component.html',
  styleUrls: ['./historial-dispositivo.component.css']
})
export class HistorialDispositivoComponent implements OnInit {
  empleado: EmpleadoNuevo = new EmpleadoNuevo('','','','','',0);
  historialEmpleados: HistorialEmpleado [] =[];
  id: number=0;

  constructor(private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              private empleadoService: EmpleadoService,
              private tokenService: TokenService,
              private historialEmpleadoService: HistorialEmpleadoService) { }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.empleadoService.detail(this.id).subscribe(
      data => {
        this.empleado = data;
        this.listarHistorialEmpleado();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
  listarHistorialEmpleado(): void {
    this.historialEmpleadoService.listarHistorialEmpleados(this.empleado.id).subscribe(
      data => {
        this.historialEmpleados = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
}
