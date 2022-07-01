import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-cadastrar-alunos',
  templateUrl: './cadastrar-alunos.component.html',
  styleUrls: ['./cadastrar-alunos.component.css']
})
export class CadastrarAlunosComponent implements OnInit {
  mensagem = "";

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) { }

  ngOnInit(): void {
    if (!this.authHelper.isAuthenticated()) {
      window.alert("Acesso negado");
      window.location.href = "/";
    }

  }

  formCadastro = new FormGroup({
    nome: new FormControl('', [Validators.required]),
    endereco: new FormControl('', [Validators.required])
  })

  get form(): any {
    return this.formCadastro.controls;
  }

  onSubmit(): void {
    this.httpClient.post(
      environment.apiUrl + "/alunos",
      this.formCadastro.value,
      { responseType: 'text' })
      .subscribe(
        data => {
          this.mensagem = data;
          this.formCadastro.reset();
        },
        e => {
          this.mensagem = "Ocorreu um erro, o cadastro não foi realizado";
          console.log(e);
        }
      )


  }

}
