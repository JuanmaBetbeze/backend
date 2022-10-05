import { Component, OnInit } from '@angular/core';
import {DispositivoNuevo} from '../../models/DispositivoNuevo';
import {TokenService} from '../../service/token.service';
import {DispositivoService} from '../../service/dispositivo.service';
import {TipoDispositivoService} from '../../service/TipoDispositivo.service';
import {MarcaService} from '../../service/Marca.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
@Component({
  selector: 'app-listar',
  templateUrl: './listarDispositivos.component.html',
  styleUrls: ['./listarDispositivos.component.css']
})
export class ListarDispositivosComponent implements OnInit {
  dispositivos: DispositivoNuevo []=[];
  permitido = false;
  roles: string []=[];
  filtrado = '';
  valor = '';
  tipos: string []=[];
  marcas: string []=[];
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
  downloadPDF(): void{
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
