import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-editar-turmas',
  templateUrl: './editar-turmas.component.html',
  styleUrls: ['./editar-turmas.component.css']
})
export class EditarTurmasComponent implements OnInit {

  mensagem = "";
  cursos: any[] = [];
  professores: any[] = [];
  alunos: any[] = [];


  constructor(private httpClient: HttpClient, private activeRoute: ActivatedRoute, private authHelper: AuthHelper) { }

  formEdicao = new FormGroup({
    idTurma: new FormControl('', []),
    ano: new FormControl('', [Validators.required]),
    semestre: new FormControl('', [Validators.required]),
    idCurso: new FormControl('', []),
    idProfessor: new FormControl('', []),
  })
  get form(): any {
    return this.formEdicao.controls;
  }



  onSubmit(): void {
    const idTurma = this.activeRoute.snapshot.paramMap.get('id') as string;
    this.httpClient.put(environment.apiUrl + "/turmas/" + idTurma, this.formEdicao.value,
      { responseType: 'text' })
      .subscribe(
        data => {
          this.mensagem = data;
        },
        e => {
          this.mensagem = "Ocorreu um erro, a edição não foi realizada."
          console.log(e);
        }
      )
  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
      const idTurma = this.activeRoute.snapshot.paramMap.get('id') as string;

      this.httpClient.get(environment.apiUrl + "/turmas/" + idTurma)
        .subscribe(
          (data: any) => {
            this.formEdicao.patchValue(data);
          },
          (e) => {
            console.log(e);
          }
        )

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
            this.alunos = data as any[];
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

    } else {
      window.alert("Acesso negado");
      window.location.href = "/";
    }


  }
}
