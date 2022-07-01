import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-alunos',
  templateUrl: './consultar-alunos.component.html',
  styleUrls: ['./consultar-alunos.component.css']
})
export class ConsultarAlunosComponent implements OnInit {

  mensagem = "";

  alunos: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
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


  excluir(idAluno: number): void {
    if (window.confirm('Deseja realmente excluir o aluno selecionado?')) {
      this.httpClient.delete(
        environment.apiUrl + '/alunos/' + idAluno,
        { responseType: 'text' })
        .subscribe(
          (data) => {
            alert(data);
            this.ngOnInit();
          },
          (e) => {
            console.log(e);
          }
        )

    }
  }

}
