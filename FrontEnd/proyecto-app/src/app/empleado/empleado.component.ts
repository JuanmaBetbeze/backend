import { Component, OnInit } from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../service/token.service';
import {SectorService} from '../service/sector.service';
import {Router} from '@angular/router';
import {PuestoService} from '../service/puesto.service';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {
  isAdmin = false;
  roles: string[];

  constructor(    private sectorService: SectorService,
                  private puestoService: PuestoService,
                  private toastr: ToastrService,
                  private tokenService: TokenService,
                  private router: Router) { }

  ngOnInit() {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
  }
}
