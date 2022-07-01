import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-turmas',
  templateUrl: './consultar-turmas.component.html',
  styleUrls: ['./consultar-turmas.component.css']
})
export class ConsultarTurmasComponent implements OnInit {

  mensagem2: any;
  mensagem = "";
  alunos: any[] = [];
  turmas: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
      this.httpClient.get(
        environment.apiUrl + '/turmas')
        .subscribe(
          (data) => {
            this.turmas = data as any[];
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

    } else {
      window.alert("Acesso negado");
      window.location.href = "/";
    }
  }

  @ViewChild("raAluno") raAluno: ElementRef | undefined;
  adicionarAluno(idTurma: number): void {
    let idAluno = this.raAluno?.nativeElement.value;
    this.httpClient.get(
      environment.apiUrl + '/turmas/addAluno/' + idTurma + "/" + idAluno,
      { responseType: 'text' })
      .subscribe(
        (data) => {
          this.mensagem = data;
          this.ngOnInit();
        },
        (e) => {
          this.mensagem = e.error;
          console.log(e);
        }
      )
  }

  excluirAluno(idTurma: number, idAluno: number): void {
    if (window.confirm('Deseja excluir o aluno da turma?')) {
      this.httpClient.get(
        environment.apiUrl + '/turmas/delAluno/' + idTurma + "/" + idAluno,
        { responseType: 'text' })
        .subscribe(
          (data) => {
            this.mensagem = data;
            this.ngOnInit();
          },
          (e) => {
            console.log(e);
          }
        )
    }
  }

  excluir(idTurma: number): void {
    if (window.confirm('Deseja realmente excluir a turma selecionada?')) {
      this.httpClient.delete(
        environment.apiUrl + '/turmas/' + idTurma,
        { responseType: 'text' })
        .subscribe(
          (data) => {
            this.mensagem = data;
            this.ngOnInit();
          },
          (e) => {
            console.log(e);
          }
        )
    }
  }
}
