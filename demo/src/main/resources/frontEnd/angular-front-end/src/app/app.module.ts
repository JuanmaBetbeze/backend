import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaProductoComponent } from './Usuario/Listar/lista-producto.component';
import { DetalleProductoComponent } from './Usuario/detalle-producto.component';
import { NuevoProductoComponent } from './Usuario/Nuevo/nuevo-producto.component';
import { EditarProductoComponent } from './Usuario/Eliminar/editar-producto.component';
import { interceptorProvider } from './interceptors/prod-interceptor.service';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { IndexComponent } from './index/index.component';
import { EmpleadoComponent } from './empleado/empleado.component';
import { NuevoEmpleadoComponent } from './empleado/nuevo-empleado/nuevo-empleado.component';
import { EditarEmpleadoComponent } from './empleado/editar-empleado/editar-empleado.component';
import { NuevoDispositivoComponent } from './dispositivo/nuevo/nuevoDispositivo.component';
import { ListarDispositivosComponent } from './dispositivo/listar/listarDispositivos.component';
import { DispositivosEmpleadosComponent } from './empleado/dispositivos-empleados/dispositivos-empleados.component';
import { AsignarDispositivoComponent } from './empleado/dispositivos-empleados/asignar-dispositivo/asignar-dispositivo.component';
import { EditarComponent } from './dispositivo/editar/editar.component';
import { HistorialComponent } from './dispositivo/historial/historial.component';
import { HistorialDispositivoComponent } from './empleado/historial-dispositivo/historial-dispositivo.component';
import { DeshabilitadoComponent } from './dispositivo/deshabilitado/deshabilitado.component';




@NgModule({
  declarations: [
    AppComponent,
    ListaProductoComponent,
    DetalleProductoComponent,
    NuevoProductoComponent,
    EditarProductoComponent,
    LoginComponent,
    MenuComponent,
    IndexComponent,
    EmpleadoComponent,
    NuevoEmpleadoComponent,
    EditarEmpleadoComponent,
    NuevoDispositivoComponent,
    ListarDispositivosComponent,
    DispositivosEmpleadosComponent,
    AsignarDispositivoComponent,
    EditarComponent,
    HistorialComponent,
    HistorialDispositivoComponent,
    DeshabilitadoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
