import { Component, OnInit } from '@angular/core';
import {UsuarioModel} from "../../Model/UsuarioModel";
import {Router} from "@angular/router";
import {AuthService} from "../../Service/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title = 'proyecto-app';
  usuario:UsuarioModel=new UsuarioModel();
  constructor(private router:Router,private authService:AuthService) { }

  ngOnInit(): void {
  }
  ValidarLogIn(){
    console.log(this.usuario)
    this.authService.singin(this.usuario).subscribe((result)=>{
      alert("Login success")
      this.authService.ObserverAuth();
      this.router.navigate(["listar"])
    },error => alert("Usuario o contraseña incorrecta")    );

  }
}
