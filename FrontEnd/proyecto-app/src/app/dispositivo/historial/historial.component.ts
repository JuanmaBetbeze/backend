import { Component, OnInit } from '@angular/core';
import {Empleado} from '../../models/empleado';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {EmpleadoService} from '../../service/empleado.service';
import {TokenService} from '../../service/token.service';
import {Dispositivo} from '../../models/Dispositivo';
import {DispositivoService} from '../../service/dispositivo.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {
  empleadoActual: Empleado = new Empleado();
  dispositivo: Dispositivo = new Dispositivo();
  id: number;
  constructor(private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              private empleadoService: EmpleadoService,
              private tokenService: TokenService,
              private dispositivosService: DispositivoService) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params.id;
    this.detallesDispositivo();
  }
  detallesDispositivo(): void {
    this.dispositivosService.detail(this.id).subscribe(
      data => {
        this.dispositivo = data;
        this.detallesEmpleadoActual();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
  detallesEmpleadoActual(): void {
    this.empleadoService.detail(this.dispositivo.empleadoActual).subscribe(
      data => {
        this.empleadoActual = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
