import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-editar-alunos',
  templateUrl: './editar-alunos.component.html',
  styleUrls: ['./editar-alunos.component.css']
})
export class EditarAlunosComponent implements OnInit {

  mensagem = "";

  constructor(private httpClient: HttpClient, private activeRoute: ActivatedRoute, private authHelper: AuthHelper) { }

  formEdicao = new FormGroup({
    idAluno: new FormControl('', []),
    nome: new FormControl('', [Validators.required]),
    endereco: new FormControl('', [Validators.required])
  })

  get form(): any {
    return this.formEdicao.controls;
  }
  onSubmit(): void {
    const idAluno = this.activeRoute.snapshot.paramMap.get('id') as string;
    this.httpClient.put(environment.apiUrl + "/alunos/" + idAluno, this.formEdicao.value,
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
      const idAluno = this.activeRoute.snapshot.paramMap.get('id') as string;

      this.httpClient.get(environment.apiUrl + "/alunos/" + idAluno)
        .subscribe(
          (data: any) => {
            this.formEdicao.patchValue(data);
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
