import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "../Service/auth.service";


@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router
    ) {  }
    canActivate():boolean{

        if(!this.authService.isAuthObserver()){
            console.log('No se inicio sesión');
            this.router.navigate(['login']);
            return false;
        }
        return true;
    }

}