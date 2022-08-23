import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import decode from 'jwt-decode';
import {AuthService} from "../Service/auth.service";

@Injectable({
    providedIn: 'root'
})
export class RoleGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        public router: Router
    ){ }
    canActivate(route: ActivatedRouteSnapshot):boolean{
        const expectedRole = route.data['expectedRole'];
        const token = localStorage.getItem('token');

        // @ts-ignore
        const { userName, roleId } = decode(token);
        console.log(roleId);

        if( !this.authService.isAuth() || roleId !== expectedRole){
            console.log('Usuario no autorizado para la vista');
            this.router.navigate(['login']);
            return false;
        }
        return true;
    }

}