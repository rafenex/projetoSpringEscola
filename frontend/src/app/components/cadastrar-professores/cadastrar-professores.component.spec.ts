import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarProfessoresComponent } from './cadastrar-professores.component';

describe('CadastrarProfessoresComponent', () => {
  let component: CadastrarProfessoresComponent;
  let fixture: ComponentFixture<CadastrarProfessoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarProfessoresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarProfessoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
