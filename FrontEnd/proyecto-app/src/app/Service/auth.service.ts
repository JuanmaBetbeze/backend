
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import {UsuarioModel} from "../Model/UsuarioModel";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private URL = 'http://localhost:8080/usuario';
    authAdmin:boolean=false;
    authEditor:boolean=false;
    authObserver:boolean=false;
    constructor(
        private http: HttpClient,
        private jwtHelper: JwtHelperService) { }

    getUsuarios(){
        return this.http.get<UsuarioModel[]>(this.URL);
    }

    singin(user:UsuarioModel){
        return this.http.post<UsuarioModel>(this.URL+'/login',user);
    }

    isAuthAdmin():boolean{
        return this.authAdmin;
    }
    isAuthEditor():boolean{
        return this.authEditor;
    }
    isAuthObserver():boolean{
        return this.authObserver;
    }
    AdminAuth():void{
        this.authAdmin=true;
    }
    EditorAuth():void{
        this.authEditor=true;
    }
    ObserverAuth():void{
        this.authObserver=true;
    }

}