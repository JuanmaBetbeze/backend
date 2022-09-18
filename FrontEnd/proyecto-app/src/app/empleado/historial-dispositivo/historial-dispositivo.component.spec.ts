import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialDispositivoComponent } from './historial-dispositivo.component';

describe('HistorialDispositivoComponent', () => {
  let component: HistorialDispositivoComponent;
  let fixture: ComponentFixture<HistorialDispositivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistorialDispositivoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialDispositivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
