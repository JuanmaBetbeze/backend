import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarDispositivoComponent } from './asignar-dispositivo.component';

describe('AsignarDispositivoComponent', () => {
  let component: AsignarDispositivoComponent;
  let fixture: ComponentFixture<AsignarDispositivoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsignarDispositivoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignarDispositivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
