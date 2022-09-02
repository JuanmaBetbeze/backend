import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaProductoComponent } from './Usuario/Listar/lista-producto.component';
import { NuevoProductoComponent } from './Usuario/Nuevo/nuevo-producto.component';
import { LoginComponent } from './login/login.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';
import {EmpleadoComponent} from './empleado/empleado.component';
import {NuevoEmpleadoComponent} from './empleado/nuevo-empleado/nuevo-empleado.component';
import {EditarEmpleadoComponent} from './empleado/editar-empleado/editar-empleado.component';
import {ListarDispositivosComponent} from './dispositivo/listar/listarDispositivos.component';
import {NuevoDispositivoComponent} from './dispositivo/nuevo/nuevoDispositivo.component';




const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'usuarios', component: ListaProductoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN'] } },
  { path: 'usuarios/nuevo', component: NuevoProductoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN'] } },
  {path: 'empleados', component: EmpleadoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN', 'EDITOR', 'OBSERVER'] }},
  {path: 'empleados/nuevo', component: NuevoEmpleadoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN', 'EDITOR'] }},
  {path: 'dispositivos', component: ListarDispositivosComponent, canActivate: [guard], data:
      { expectedRol: ['ADMIN', 'EDITOR', 'OBSERVER'] }},
  {path: 'dispositivos/nuevo', component: NuevoDispositivoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN', 'EDITOR'] }},
  // { path: 'detalle/:id', component: DetalleProductoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
   { path: 'empleados/:id/editar', component: EditarEmpleadoComponent, canActivate: [guard], data: { expectedRol: ['ADMIN', 'EDITOR'] } },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
