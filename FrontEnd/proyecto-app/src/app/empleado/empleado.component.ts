import { Component, OnInit } from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';
import {EmpleadoService} from '../service/empleado.service';
import {Empleado} from '../models/empleado';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {
  isAdmin = false;
  roles: string[];
  empleados: Empleado [] = [];

  constructor(    private toastr: ToastrService,
                  private tokenService: TokenService,
                  private empleadoService: EmpleadoService,
                  private router: Router) { }

  ngOnInit() {
    this.listarEmpleados();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
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
}
