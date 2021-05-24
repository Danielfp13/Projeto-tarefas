import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TarefaComponent } from './tarefa/tarefa.component';
import { TarefaFormComponent } from './tarefa-form/tarefa-form.component';
import { TarefaRoutingModule } from './tarefa-routing.module';
import { FormsModule } from '@angular/forms';
import { ConvidadoComponent } from './convidado/convidado.component';




@NgModule({
  declarations: [
    TarefaComponent,
    TarefaFormComponent,
    ConvidadoComponent
  ],
  imports: [
    CommonModule,
    TarefaRoutingModule,
    FormsModule
  ],exports: [
    TarefaComponent,
    TarefaFormComponent,
    ConvidadoComponent
  ]
})
export class TarefaModule { }
