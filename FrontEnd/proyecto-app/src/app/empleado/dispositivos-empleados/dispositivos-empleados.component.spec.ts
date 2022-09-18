import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DispositivosEmpleadosComponent } from './dispositivos-empleados.component';

describe('DispositivosEmpleadosComponent', () => {
  let component: DispositivosEmpleadosComponent;
  let fixture: ComponentFixture<DispositivosEmpleadosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DispositivosEmpleadosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DispositivosEmpleadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
