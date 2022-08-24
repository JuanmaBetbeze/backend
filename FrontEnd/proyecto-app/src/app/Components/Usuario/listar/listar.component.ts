import { Component, OnInit } from '@angular/core';
import {UsuarioModel} from "../../../Model/UsuarioModel";
import {AuthService} from "../../../Service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
usuarios:UsuarioModel[];
  constructor(private service:AuthService,private router:Router) { }

  ngOnInit() {
    this.service.getUsuarios().subscribe(resultado=>{
      this.usuarios=resultado;
    })
  }

}
