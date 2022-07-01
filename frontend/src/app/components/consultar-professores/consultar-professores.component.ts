import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-professores',
  templateUrl: './consultar-professores.component.html',
  styleUrls: ['./consultar-professores.component.css']
})
export class ConsultarProfessoresComponent implements OnInit {

  mensagem = "";

  professores: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
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


  excluir(idProfessor: number): void {
    if (window.confirm('Deseja realmente excluir o professor selecionado?')) {
      this.httpClient.delete(
        environment.apiUrl + '/professores/' + idProfessor,
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
