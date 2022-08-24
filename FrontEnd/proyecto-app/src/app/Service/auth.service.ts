
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
    constructor(
        private http: HttpClient,
        private jwtHelper: JwtHelperService) { }

    getUsuarios(){
        return this.http.get<UsuarioModel[]>(this.URL);
    }

    singin(user:UsuarioModel){
        return this.http.post<UsuarioModel>(this.URL+'/login',user);
    }

    isAuth():boolean{
        const token = localStorage.getItem('token');
        // @ts-ignore
        if(this.jwtHelper.isTokenExpired(token) || !localStorage.getItem('token')){
            return false;
        }
        return true;
    }
}