import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild, ViewRef } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cadastrar-turmas',
  templateUrl: './cadastrar-turmas.component.html',
  styleUrls: ['./cadastrar-turmas.component.css']
})
export class CadastrarTurmasComponent implements OnInit {


  mensagem = "";
  cursos: any[] = [];
  professores: any[] = [];
  alunosBd: any[] = [];
  alunos: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) { }

  ngOnInit(): void {
    if (!this.authHelper.isAuthenticated()) {
      window.alert("Acesso negado");
      window.location.href = "/";
    }

    this.httpClient.get(
      environment.apiUrl + '/cursos')
      .subscribe(
        (data) => {
          this.cursos = data as any[];
        },
        (e) => {
          console.log(e);
        }
      )

    this.httpClient.get(
      environment.apiUrl + '/alunos')
      .subscribe(
        (data) => {
          this.alunosBd = data as any[];
        },
        (e) => {
          console.log(e);
        }
      )

    this.httpClient.get(
      environment.apiUrl + '/professores')
      .subscribe(
        (data) => {
          this.professores = data as any[];
        },
        (e) => {
          console.log(e);
        }
      )
  }

  estaNaLista = false;
  mairQue40 = false;

  // https://www.telerik.com/blogs/angular-basics-how-to-get-value-selected-dropdown-menu-item
  @ViewChild("alunobd") aluno: ElementRef | undefined;
  adicionar(): void {
    let aluno = this.aluno?.nativeElement.value;

    this.alunos.forEach(a => {
      if (a == aluno) {
        alert("Aluno repetido")
        this.estaNaLista = true;
      }
    });

    if (this.alunos.length >= 40) {
      this.mairQue40 = true;
      alert("Turma cheia")
    }

    if (!this.estaNaLista && !this.mairQue40) {
      this.alunos.push(aluno);
      this.mensagem = "Alunos adicionados:" + this.alunos.length;
    }

    this.estaNaLista = false;
    this.mairQue40 = false;

  }


  formCadastro = new FormGroup({
    semestre: new FormControl(''),
    ano: new FormControl(''),
    idCurso: new FormControl(''),
    idProfessor: new FormControl(''),
    alunos: new FormControl('')
  })

  get form(): any {
    return this.formCadastro.controls;
  }


  onSubmit(): void {
    console.log(this.alunos)
    let objeto: any = {
      alunos: this.alunos,
      ano: this.formCadastro.value.ano,
      idCurso: this.formCadastro.value.idCurso,
      idProfessor: this.formCadastro.value.idProfessor,
      semestre: this.formCadastro.value.semestre
    }

    console.log(objeto)
    this.httpClient.post(
      environment.apiUrl + "/turmas",
      objeto,
      { responseType: 'text' })
      .subscribe(
        data => {
          this.mensagem = data;
          this.formCadastro.reset();
          window.location.href = "/mostrar-detalhes";
        },
        e => {
          this.mensagem = e.error;
          ;
          console.log(e);
        }

      )



  }

}
