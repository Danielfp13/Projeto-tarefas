import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../guard/auth.guard';
import { TarefaComponent } from './tarefa/tarefa.component';
import { TarefaFormComponent } from './tarefa-form/tarefa-form.component';
import { ConvidadoComponent } from './convidado/convidado.component';



const routes: Routes = [

  { path: 'tarefa', component: LayoutComponent, 
    canActivate: [AuthGuard], children: [

      { path: 'form', component: TarefaFormComponent },
      { path: 'form/:id', component: TarefaFormComponent },
      { path: 'lista', component: TarefaComponent },
      { path: 'convidado', component: ConvidadoComponent },
      { path: '', redirectTo: '/tarefa/lista', pathMatch: 'full' }

    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TarefaRoutingModule { }
