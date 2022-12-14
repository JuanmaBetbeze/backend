import { Component, OnInit } from '@angular/core';
import {TipoDispositivoService} from '../../service/TipoDispositivo.service';
import {MarcaService} from '../../service/Marca.service';
import {DispositivoService} from '../../service/dispositivo.service';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../service/token.service';
import {ActivatedRoute, Router} from '@angular/router';
import {DispositivoNuevo} from '../../models/DispositivoNuevo';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {
  dispositivo: DispositivoNuevo = new DispositivoNuevo('','','','','',0,false,
    0,false,'',0,'');
  tipos: string []=[];
  marcas: string []=[];
  ejecutor: string='';
  motivo: string='';
  desabilitar = false;
  constructor(private activatedRoute: ActivatedRoute,
              private tipoDispositivo: TipoDispositivoService,
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
    const id = this.activatedRoute.snapshot.params['id'];
    if (this.tokenService.getToken()) {
      this.ejecutor = this.tokenService.getUserName();
    }
    this.dispositivosService.detail(id).subscribe(
      data => {
        this.dispositivo = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.dispositivosService.update(id, this.dispositivo).subscribe(data => {
        this.toastr.success('Dispositivo actualizado', 'OK', {
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
  borrar(): void {
    this.dispositivosService.delete(this.dispositivo.id).subscribe(
      data => {
        this.toastr.success('Dispositivo Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/dispositivos']).then(() => {
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
  deshabilitar(): void {
    this.dispositivosService.deshabilitar(this.dispositivo.id,this.ejecutor,this.motivo).subscribe(
      data => {
        this.toastr.success('Dispositivo Deshabilitado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/dispositivos']).then(() => {
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
  habilitar(): void {
    this.dispositivosService.habilitar(this.dispositivo.id).subscribe(
      data => {
        this.toastr.success('Dispositivo Habilitado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/dispositivos']).then(() => {
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

}
