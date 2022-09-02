import { Component, OnInit } from '@angular/core';
import {Dispositivo} from '../../models/Dispositivo';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../service/token.service';
import {Router} from '@angular/router';
import {TipoDispositivoService} from '../../service/TipoDispositivo.service';
import {MarcaService} from '../../service/Marca.service';
import {DispositivoService} from '../../service/dispositivo.service';

@Component({
  selector: 'app-nuevo',
  templateUrl: './nuevoDispositivo.component.html',
  styleUrls: ['./nuevoDispositivo.component.css']
})
export class NuevoDispositivoComponent implements OnInit {
  dispositivo: Dispositivo = new Dispositivo();
  tipos: string [];
  marcas: string [];
  tipoNew: string;
  marcaNew: string;
  constructor(private tipoDispositivo: TipoDispositivoService,
              private marcaService: MarcaService,
              private dispositivosService: DispositivoService,
              private toastr: ToastrService,
              private tokenService: TokenService,
              private router: Router) { }

  ngOnInit() {
    this.cargarTipos();
    this.cargarMarcas();
    this.dispositivo.tipo = '';
    this.dispositivo.marca = '';
  }
  onCreate(): void {
    this.dispositivosService.nuevo(this.dispositivo).subscribe(data => {
        this.toastr.success('Dispositivo Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/dispositivos']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }
  onCreateTipo(): void {
    this.tipoDispositivo.nuevo(this.tipoNew).subscribe(data => {
        this.toastr.success('Tipo Creado', 'OK', {
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
  onCreateMarca(): void {
    this.marcaService.nuevo(this.marcaNew).subscribe(data => {
        this.toastr.success('Marca Creada', 'OK', {
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
  cargarTipos(): void {
    this.tipoDispositivo.listarTipos().subscribe(
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

}
