import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListarComponent} from "./Components/Usuario/listar/listar.component";
import {AddComponent} from "./Components/Usuario/add/add.component";
import {EditComponent} from "./Components/Usuario/edit/edit.component";
import {HomeComponent} from "./Components/home/home.component";
import { AuthGuard } from './guards/auth.guards';
import { RoleGuard } from './guards/role.guards';
import {LoginComponent} from "./Components/login/login.component";
const routes: Routes = [
  {path:'listar',component:ListarComponent, canActivate: [AuthGuard]},
  {path:'add',component:AddComponent, canActivate: [AuthGuard] },
  {path:'edit',component:EditComponent, canActivate: [AuthGuard] },
  {path:'home',component:HomeComponent, canActivate: [AuthGuard]},
  {path:'login',component:LoginComponent},
  { path: '**', pathMatch: 'full', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
