import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaProductoComponent } from './Usuario/Listar/lista-producto.component';
import { DetalleProductoComponent } from './Usuario/detalle-producto.component';
import { NuevoProductoComponent } from './Usuario/Nuevo/nuevo-producto.component';
import { EditarProductoComponent } from './Usuario/Eliminar/editar-producto.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './RegistrarUsuarios/registro.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';



const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent, canActivate: [guard], data: { expectedRol: ['admin'] }},
  { path: 'usuarios', component: ListaProductoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: 'detalle/:id', component: DetalleProductoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: 'usuarios/nuevo', component: NuevoProductoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'editar/:id', component: EditarProductoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
