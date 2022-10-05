import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class ProdGuardService implements CanActivate {

  realRol: boolean=false;

  constructor(
    private tokenService: TokenService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data['expectedRol'];
    const roles = this.tokenService.getAuthorities();
    this.realRol = false;
    roles.forEach(rol => {
      if (expectedRol.indexOf(rol) === -1) {
      } else {
        this.realRol = true;
      }
    });
    if (!this.tokenService.getToken() || !this.realRol) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
