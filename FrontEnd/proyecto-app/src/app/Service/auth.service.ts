
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import {Usuario} from "../Model/Usuario";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private URL = 'http://localhost:8080/usuario';
    constructor(
        private http: HttpClient,
        private jwtHelper: JwtHelperService) { }

    getUsuarios(){
        return this.http.get<Usuario[]>(this.URL);
    }

    singin(user:Usuario){
        return this.http.post<Usuario>(this.URL,user);
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