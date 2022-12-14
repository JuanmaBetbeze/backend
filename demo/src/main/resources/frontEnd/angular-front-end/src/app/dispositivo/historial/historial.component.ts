import { Component, OnInit } from '@angular/core';
import {EmpleadoNuevo} from '../../models/EmpleadoNuevo';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {EmpleadoService} from '../../service/empleado.service';
import {TokenService} from '../../service/token.service';
import {DispositivoNuevo} from '../../models/DispositivoNuevo';
import {DispositivoService} from '../../service/dispositivo.service';
import {HistorialDispositivo} from '../../models/historialDispositivo';
import {HistorialDispositivoService} from '../../service/HistorialDispositivo.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {
  dispositivo: DispositivoNuevo = new DispositivoNuevo('','','','','',-1,false,-1,
    false,'',0,'');
  id: number = -1;
  historialDispositivos: HistorialDispositivo [] = [];
  constructor(private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              private empleadoService: EmpleadoService,
              private tokenService: TokenService,
              private dispositivosService: DispositivoService,
              private historialDispositivoService: HistorialDispositivoService) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.detallesDispositivo();
  }
  detallesDispositivo(): void {
    this.dispositivosService.detail(this.id).subscribe(
      data => {
        this.dispositivo = data;
        this.listarHistorialDispositivo();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
  listarHistorialDispositivo(): void {
    this.historialDispositivoService.listarHistorialDispositivo(this.dispositivo.id).subscribe(
      data => {
        this.historialDispositivos = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
