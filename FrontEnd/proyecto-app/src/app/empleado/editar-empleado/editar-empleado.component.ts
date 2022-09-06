import { Component, OnInit } from '@angular/core';
import {Empleado} from '../../models/empleado';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {EmpleadoService} from '../../service/empleado.service';
import {PuestoService} from '../../service/puesto.service';
import {SectorService} from '../../service/sector.service';

@Component({
  selector: 'app-editar-empleado',
  templateUrl: './editar-empleado.component.html',
  styleUrls: ['./editar-empleado.component.css']
})
export class EditarEmpleadoComponent implements OnInit {
  empleado: Empleado = new Empleado();
  sectores: string [];
  puestos: string [];

  constructor(private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              private empleadoService: EmpleadoService,
              private puestoService: PuestoService,
              private sectorService: SectorService
  ) {
  }

  ngOnInit() {
    this.cargarSectores();
    this.cargarPuestos();
    const id = this.activatedRoute.snapshot.params.id;
    this.empleadoService.detail(id).subscribe(
      data => {
        this.empleado = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.empleadoService.update(id, this.empleado).subscribe(
      data => {
        this.toastr.success('Empleado Actualizado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/empleados']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }
  borrar(): void {
    this.empleadoService.delete(this.empleado.id).subscribe(
      data => {
        this.toastr.success('Producto Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/empleados']).then(() => {
          window.location.reload();
        });
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
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
