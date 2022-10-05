import { Component, OnInit } from '@angular/core';
import {DispositivoNuevo} from "../../models/DispositivoNuevo";
import {TokenService} from "../../service/token.service";
import {DispositivoService} from "../../service/dispositivo.service";

@Component({
  selector: 'app-deshabilitado',
  templateUrl: './deshabilitado.component.html',
  styleUrls: ['./deshabilitado.component.css']
})
export class DeshabilitadoComponent implements OnInit {
  dispositivos: DispositivoNuevo []=[];
  permitido = false;
  roles: string []=[];
  filtrarList: string[] = [];
  constructor(private tokenService: TokenService,
              private dispositivoService: DispositivoService) { }

  ngOnInit(): void {
    this.listarDispositivos();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN' || rol === 'EDITOR') {
        this.permitido = true;
      }
    });
  }
  listarDispositivos(): void {
    this.filtrarList.push('deshabilitado');
    this.filtrarList.push('SI');
    this.dispositivoService.filtrarDispositivo(this.filtrarList).subscribe(
      data => {
        this.dispositivos = data;
        this.filtrarList.pop();
        this.filtrarList.pop();
      },
      err => {
        console.log(err);
      }
    );
  }

}
