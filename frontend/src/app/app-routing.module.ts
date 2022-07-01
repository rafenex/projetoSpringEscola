
import { MostrarDetalhesComponent } from './components/mostrar-detalhes/mostrar-detalhes.component';
import { EditarProfessoresComponent } from './components/editar-professores/editar-professores.component';
import { ConsultarProfessoresComponent } from './components/consultar-professores/consultar-professores.component';
import { CadastrarProfessoresComponent } from './components/cadastrar-professores/cadastrar-professores.component';
import { EditarAlunosComponent } from './components/editar-alunos/editar-alunos.component';
import { ConsultarAlunosComponent } from './components/consultar-alunos/consultar-alunos.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarTurmasComponent } from './components/cadastrar-turmas/cadastrar-turmas.component';
import { ConsultarTurmasComponent } from './components/consultar-turmas/consultar-turmas.component';
import { EditarTurmasComponent } from './components/editar-turmas/editar-turmas.component';
import { CadastrarCursosComponent } from './components/cadastrar-cursos/cadastrar-cursos.component';
import { ConsultarCursosComponent } from './components/consultar-cursos/consultar-cursos.component';
import { EditarCursosComponent } from './components/editar-cursos/editar-cursos.component';
import { CadastrarAlunosComponent } from './components/cadastrar-alunos/cadastrar-alunos.component';
import { AccountComponent } from './components/account/account.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: 'cadastrar-alunos', component: CadastrarAlunosComponent },
  { path: 'consultar-alunos', component: ConsultarAlunosComponent },
  { path: 'editar-alunos/:id', component: EditarAlunosComponent },

  { path: 'cadastrar-professores', component: CadastrarProfessoresComponent },
  { path: 'consultar-professores', component: ConsultarProfessoresComponent },
  { path: 'editar-professores/:id', component: EditarProfessoresComponent },

  { path: 'cadastrar-cursos', component: CadastrarCursosComponent },
  { path: 'consultar-cursos', component: ConsultarCursosComponent },
  { path: 'editar-cursos/:id', component: EditarCursosComponent },


  { path: 'cadastrar-turmas', component: CadastrarTurmasComponent },
  { path: 'consultar-turmas', component: ConsultarTurmasComponent },
  { path: 'editar-turmas/:id', component: EditarTurmasComponent },

  { path: 'mostrar-detalhes', component: MostrarDetalhesComponent },
  { path: '', component: LoginComponent },
  { path: 'account', component: AccountComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
