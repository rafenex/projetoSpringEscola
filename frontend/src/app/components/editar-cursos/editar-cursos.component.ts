import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-editar-cursos',
  templateUrl: './editar-cursos.component.html',
  styleUrls: ['./editar-cursos.component.css']
})
export class EditarCursosComponent implements OnInit {

  mensagem = "";

  constructor(private httpClient: HttpClient, private activeRoute: ActivatedRoute, private authHelper: AuthHelper) { }

  formEdicao = new FormGroup({
    idCurso: new FormControl('', []),
    nome: new FormControl('', [Validators.required]),
    sigla: new FormControl('', [Validators.required])
  })

  get form(): any {
    return this.formEdicao.controls;
  }
  onSubmit(): void {
    const idCurso = this.activeRoute.snapshot.paramMap.get('id') as string;
    this.httpClient.put(environment.apiUrl + "/cursos/" + idCurso, this.formEdicao.value,
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
      const idCurso = this.activeRoute.snapshot.paramMap.get('id') as string;

      this.httpClient.get(environment.apiUrl + "/cursos/" + idCurso)
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
