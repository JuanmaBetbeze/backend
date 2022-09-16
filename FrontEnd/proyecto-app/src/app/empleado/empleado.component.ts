import { Component, OnInit } from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';
import {EmpleadoService} from '../service/empleado.service';
import {Empleado} from '../models/empleado';
import {SectorService} from '../service/sector.service';
import {PuestoService} from '../service/puesto.service';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {
  permitido = false;
  roles: string[];
  empleados: Empleado [] = [];
  filtrado = '';
  valor = '';
  filtrarList: string[] = [];
  puestos: string[];
  sectores: string[];


  constructor(    private toastr: ToastrService,
                  private tokenService: TokenService,
                  private empleadoService: EmpleadoService,
                  private router: Router,
                  private sectorService: SectorService,
                  private puestoService: PuestoService) { }

  ngOnInit() {
    this.listarEmpleados();
    this.cargarPuestos();
    this.cargarSectores();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN' || rol === 'EDITOR') {
        this.permitido = true;
      }
    });
  }
  listarEmpleados() {
    this.empleadoService.listarEmpleados().subscribe(data => {
      this.empleados = data;
    },
      err => {
        console.log(err);
      }
    );
  }
  filtrar(): void {
    this.filtrarList.push(this.filtrado);
    this.filtrarList.push(this.valor);
    this.empleadoService.filtrarEmpleados(this.filtrarList).subscribe(
      data => {
        this.empleados = data;
        this.filtrarList.pop();
        this.filtrarList.pop();
      },
      err => {
        console.log(err);
      }
    );
  }

  cambio(): void {
    if (this.filtrado === 'listar') {
      this.valor = 'listar';
    } else {
      this.valor = '';
    }
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
