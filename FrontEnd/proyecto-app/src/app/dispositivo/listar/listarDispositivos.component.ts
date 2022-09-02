import { Component, OnInit } from '@angular/core';
import {Dispositivo} from '../../models/Dispositivo';
import {TokenService} from '../../service/token.service';
import {DispositivoService} from '../../service/dispositivo.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listarDispositivos.component.html',
  styleUrls: ['./listarDispositivos.component.css']
})
export class ListarDispositivosComponent implements OnInit {
  dispositivos: Dispositivo [];
  permitido = false;
  roles: string [];

  constructor(
    private tokenService: TokenService,
    private dispositivoService: DispositivoService
  ) { }

  ngOnInit() {
    this.listarDispositivos();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN' || rol === 'EDITOR') {
        this.permitido = true;
      }
    });
  }
  listarDispositivos(): void {
    this.dispositivoService.listarEmpleados().subscribe(data => {
        this.dispositivos = data;
      },
      error => {
        console.log(error);
      });
  }
}
