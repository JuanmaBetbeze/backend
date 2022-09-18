import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarDispositivosComponent } from './listarDispositivos.component';

describe('ListarComponent', () => {
  let component: ListarDispositivosComponent;
  let fixture: ComponentFixture<ListarDispositivosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarDispositivosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarDispositivosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
