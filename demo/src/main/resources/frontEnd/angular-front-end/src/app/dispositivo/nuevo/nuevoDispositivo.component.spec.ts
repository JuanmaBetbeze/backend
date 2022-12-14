import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoDispositivoComponent } from './nuevoDispositivo.component';

describe('NuevoComponent', () => {
  let component: NuevoDispositivoComponent;
  let fixture: ComponentFixture<NuevoDispositivoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NuevoDispositivoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoDispositivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
