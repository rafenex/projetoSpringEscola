import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarDetalhesComponent } from './mostrar-detalhes.component';

describe('MostrarDetalhesComponent', () => {
  let component: MostrarDetalhesComponent;
  let fixture: ComponentFixture<MostrarDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostrarDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
