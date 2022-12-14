import { Component, OnInit } from '@angular/core';
import {SectorService} from '../../service/sector.service';
import {PuestoService} from '../../service/puesto.service';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../service/token.service';
import {Router} from '@angular/router';
import {EmpleadoNuevo} from '../../models/EmpleadoNuevo';
import {EmpleadoService} from '../../service/empleado.service';

@Component({
  selector: 'app-nuevo-empleado',
  templateUrl: './nuevo-empleado.component.html',
  styleUrls: ['./nuevo-empleado.component.css']
})
export class NuevoEmpleadoComponent implements OnInit {
  sectorNew: string='';
  puestoNew: string='';
  sectores: string []=[];
  puestos: string []=[];
  empleado: EmpleadoNuevo = new EmpleadoNuevo('','','','','',0);
  constructor(private sectorService: SectorService,
              private puestoService: PuestoService,
              private empleadoService: EmpleadoService,
              private toastr: ToastrService,
              private tokenService: TokenService,
              private router: Router) { }

  ngOnInit() {
    this.cargarSectores();
    this.cargarPuestos();
    this.empleado.puesto = '';
    this.empleado.sector = '';
  }
  onCreate(): void {
    this.empleadoService.nuevo(this.empleado).subscribe(data => {
        this.toastr.success('EmpleadoNuevo Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/empleados']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }
  onCreateSector(): void {
    this.sectorService.nuevo(this.sectorNew).subscribe(data => {
        this.toastr.success('Sector Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        window. location. reload();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }
  onCreatePuestos(): void {
    this.puestoService.nuevo(this.puestoNew).subscribe(data => {
        this.toastr.success('Puesto Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        window. location. reload();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }
  cargarSectores(): void {
    this.sectorService.listarSectores().subscribe(
      data => {
        this.sectores = data;

      },
      err => {
        console.log(err);
      }
    );
  }
  cargarPuestos(): void {
    this.puestoService.listarPuestos().subscribe(
      data => {
        this.puestos = data;

      },
      err => {
        console.log(err);
      }
    );
  }

}
