import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import {DispositivoNuevo} from '../models/DispositivoNuevo';
import {DispositivoService} from '../service/dispositivo.service';
import {TipoDispositivoService} from '../service/TipoDispositivo.service';
import {MarcaService} from '../service/Marca.service';
import {EmpleadoNuevo} from '../models/EmpleadoNuevo';
import {EmpleadoService} from '../service/empleado.service';
import {PuestoService} from '../service/puesto.service';
import {SectorService} from '../service/sector.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  isLogged = false;
  nombreUsuario = '';
  dispositivos: DispositivoNuevo []=[];
  permitido = false;
  roles: string []=[];
  filtrado = '';
  valor = '';
  tipos: string []=[];
  marcas: string []=[];
  filtrarList: string[] = [];
  categoria = '';
  empleados: EmpleadoNuevo [] = [];
  puestos: string[]=[];
  sectores: string[]=[];

  constructor(private tokenService: TokenService,
              private dispositivoService: DispositivoService,
              private tipoService: TipoDispositivoService,
              private marcaService: MarcaService,
              private empleadoService: EmpleadoService,
              private puestoService: PuestoService,
              private sectorService: SectorService) { }

  ngOnInit() {
    this.listarDispositivos();
    this.cargarTipos();
    this.cargarMarcas();
    this.listarEmpleados();
    this.cargarPuestos();
    this.cargarSectores();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
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
    if (this.categoria === 'empleado') {
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
    if (this.categoria === 'dispositivo') {
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

  cambioCategoria(): void {
      this.filtrado = 'seleccionar';
      this.valor = '';
  }
  cambio(): void {
    if (this.filtrado === 'listar') {
      this.valor = 'listar';
    } else {
      this.valor = '';
    }
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
  downloadPDF(): void {
      const DATA = document.getElementById('htmlData');
    const doc = new jsPDF('p', 'pt', 'a4');
    const options = {
      background: 'white',
      scale: 3
    };
    // @ts-ignore
    html2canvas(DATA, options).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');

      // Add image Canvas to PDF
      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`${new Date().toISOString()}_tutorial.pdf`);
    });
  }


}
