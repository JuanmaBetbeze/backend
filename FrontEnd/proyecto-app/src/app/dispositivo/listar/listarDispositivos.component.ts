import { Component, OnInit } from '@angular/core';
import {Dispositivo} from '../../models/Dispositivo';
import {TokenService} from '../../service/token.service';
import {DispositivoService} from '../../service/dispositivo.service';
import {TipoDispositivoService} from '../../service/TipoDispositivo.service';
import {MarcaService} from '../../service/Marca.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listarDispositivos.component.html',
  styleUrls: ['./listarDispositivos.component.css']
})
export class ListarDispositivosComponent implements OnInit {
  dispositivos: Dispositivo [];
  permitido = false;
  roles: string [];
  filtrado = '';
  valor = '';
  tipos: string [];
  marcas: string [];
  filtrarList: string[] = [];

  constructor(
    private tokenService: TokenService,
    private dispositivoService: DispositivoService,
    private tipoService: TipoDispositivoService,
    private marcaService: MarcaService
  ) {
  }

  ngOnInit() {
    this.listarDispositivos();
    this.cargarTipos();
    this.cargarMarcas();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN' || rol === 'EDITOR') {
        this.permitido = true;
      }
    });
  }

  listarDispositivos(): void {
    this.dispositivoService.listarDispositivos().subscribe(data => {
        this.dispositivos = data;
      },
      error => {
        console.log(error);
      });
  }

  cargarTipos(): void {
    this.tipoService.listarTipos().subscribe(
      data => {
        this.tipos = data;

      },
      err => {
        console.log(err);
      }
    );
  }

  cargarMarcas(): void {
    this.marcaService.listarMarcas().subscribe(
      data => {
        this.marcas = data;

      },
      err => {
        console.log(err);
      }
    );
  }

  filtrar(): void {
    this.filtrarList.push(this.filtrado);
    this.filtrarList.push(this.valor);
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

  cambio(): void {
    if (this.filtrado === 'listar') {
      this.valor = 'listar';
    } else {
      this.valor = '';
    }
  }
}
