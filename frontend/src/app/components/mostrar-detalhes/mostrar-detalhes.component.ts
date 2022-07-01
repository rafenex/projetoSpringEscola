import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-mostrar-detalhes',
  templateUrl: './mostrar-detalhes.component.html',
  styleUrls: ['./mostrar-detalhes.component.css']
})
export class MostrarDetalhesComponent implements OnInit {

  mensagem = "";

  turmas: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }

  ngOnInit(): void {

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

  }



}
