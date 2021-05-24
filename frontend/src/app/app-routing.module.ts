import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { AuthGuard } from './guard/auth.guard';
import { TarefaComponent } from './tarefa/tarefa/tarefa.component';



const routes: Routes = [

  { path: "login", component: LoginComponent },
  { path: '', component: LayoutComponent, children: [
      {  path: "tarefa", component: TarefaComponent, canActivate: [ AuthGuard ] },
      { path: '', redirectTo: '/tarefa', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
