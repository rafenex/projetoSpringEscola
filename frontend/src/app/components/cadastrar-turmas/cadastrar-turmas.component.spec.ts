import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarTurmasComponent } from './cadastrar-turmas.component';

describe('CadastrarTurmasComponent', () => {
  let component: CadastrarTurmasComponent;
  let fixture: ComponentFixture<CadastrarTurmasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarTurmasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarTurmasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
