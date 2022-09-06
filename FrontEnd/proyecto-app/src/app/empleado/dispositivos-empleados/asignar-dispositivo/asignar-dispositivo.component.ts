import { Component, OnInit } from '@angular/core';
import {Dispositivo} from '../../../models/Dispositivo';
import {TokenService} from '../../../service/token.service';
import {DispositivoService} from '../../../service/dispositivo.service';
import {EmpleadoService} from '../../../service/empleado.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-asignar-dispositivo',
  templateUrl: './asignar-dispositivo.component.html',
  styleUrls: ['./asignar-dispositivo.component.css']
})
export class AsignarDispositivoComponent implements OnInit {
dispositivos: Dispositivo [] = [];
idAAgregar: number [] = [];
dispositivosAAgregar: Dispositivo [] = [];
  constructor(    private tokenService: TokenService,
                  private dispositivoService: DispositivoService,
                  private empleadoService: EmpleadoService,
                  private activatedRoute: ActivatedRoute,
                  private toastr: ToastrService,
                  private router: Router) { }

  ngOnInit() {
    this.listarDispositivosSinAsignar();
  }
  listarDispositivosSinAsignar(): void {
    this.dispositivoService.listarDispositivosSinAsignar().subscribe(data => {
        this.dispositivos = data;
      },
      error => {
        console.log(error);
      });
  }
  onAsignar(): void {
    this.dispositivosAAgregar = this.dispositivos.filter(x => x.agregar);
    const id = this.activatedRoute.snapshot.params.id;
    this.dispositivosAAgregar.forEach(y => this.pushear(id, y.id));
    this.router.navigate([`/empleados/${id}/dispositivos`]).then(() => {
      window.location.reload();
    });
  }
  pushear(id: number, idAAgregar: number ): void {
    this.empleadoService.agregarDispositivos(id, idAAgregar).subscribe(data => {
        this.toastr.success('Dispositivos agregados', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }
}
