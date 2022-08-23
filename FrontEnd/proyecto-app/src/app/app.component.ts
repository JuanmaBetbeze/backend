import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "./Service/auth.service";
import {Usuario} from "./Model/Usuario";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'proyecto-app';
  pass:String;
  username:String;

  constructor(private router:Router,private authService:AuthService) {
  }
  Listar(){
    this.router.navigate(["listar"]);
  }
  Nuevo(){
    this.router.navigate(["add"]);
  }
  Home(){
    this.router.navigate(["home"]);
  }
  Validar(){
    return this.authService.getUsuarios();

  }
}
