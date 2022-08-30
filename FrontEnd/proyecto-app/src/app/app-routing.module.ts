import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaProductoComponent } from './Usuario/Listar/lista-producto.component';
import { NuevoProductoComponent } from './Usuario/Nuevo/nuevo-producto.component';
import { LoginComponent } from './login/login.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';
import {EmpleadoComponent} from './empleado/empleado.component';
import {NuevoEmpleadoComponent} from './empleado/nuevo-empleado/nuevo-empleado.component';




const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'usuarios', component: ListaProductoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN'] } },
  { path: 'usuarios/nuevo', component: NuevoProductoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN'] } },
  {path: 'empleados', component: EmpleadoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN', 'EDITOR'] }},
  {path: 'empleados/nuevo', component: NuevoEmpleadoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN'] }},

  // { path: 'detalle/:id', component: DetalleProductoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  // { path: 'editar/:id', component: EditarProductoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
