import { MostrarDetalhesComponent } from './components/mostrar-detalhes/mostrar-detalhes.component';

import { CadastrarAlunosComponent } from './components/cadastrar-alunos/cadastrar-alunos.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AccountComponent } from "./components/account/account.component";
import { CadastrarCursosComponent } from "./components/cadastrar-cursos/cadastrar-cursos.component";
import { CadastrarProfessoresComponent } from "./components/cadastrar-professores/cadastrar-professores.component";
import { CadastrarTurmasComponent } from "./components/cadastrar-turmas/cadastrar-turmas.component";
import { ConsultarAlunosComponent } from "./components/consultar-alunos/consultar-alunos.component";
import { ConsultarCursosComponent } from "./components/consultar-cursos/consultar-cursos.component";
import { ConsultarProfessoresComponent } from "./components/consultar-professores/consultar-professores.component";
import { ConsultarTurmasComponent } from "./components/consultar-turmas/consultar-turmas.component";
import { EditarAlunosComponent } from "./components/editar-alunos/editar-alunos.component";
import { EditarCursosComponent } from "./components/editar-cursos/editar-cursos.component";
import { EditarProfessoresComponent } from "./components/editar-professores/editar-professores.component";
import { EditarTurmasComponent } from "./components/editar-turmas/editar-turmas.component";
import { LoginComponent } from "./components/login/login.component";
import { AuthHelper } from "./_helpers/auth-helpers";
import { TokenInterceptor } from "./_intercepters/tokenInterceptor";
import { BackButtonComponent } from './components/back-button/back-button.component';





@NgModule({
  declarations: [
    AppComponent,
    CadastrarAlunosComponent,
    ConsultarAlunosComponent,
    EditarAlunosComponent,
    EditarProfessoresComponent,
    CadastrarProfessoresComponent,
    ConsultarProfessoresComponent,
    ConsultarTurmasComponent,
    CadastrarTurmasComponent,
    EditarTurmasComponent,
    EditarCursosComponent,
    CadastrarCursosComponent,
    ConsultarCursosComponent,
    LoginComponent,
    AccountComponent,
    MostrarDetalhesComponent,
    BackButtonComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      //Config do interceptor
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: AuthHelper
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
