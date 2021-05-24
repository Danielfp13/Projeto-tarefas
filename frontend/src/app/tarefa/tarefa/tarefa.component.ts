import { Component, EventEmitter, OnInit } from '@angular/core';
import { Tarefa } from '../Tarefa';
import { TarefaService } from '../../service/tarefa.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  tarefas: Tarefa[] = [];
  tarefaSelecionada: Tarefa;
  menssageDeResposta: string;
  cor: string = 'denger';
  idtarefa: number;

  emitirIdTarefa = new EventEmitter<number>();


  constructor(private service: TarefaService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.service.listaTarefa().subscribe(resposta => {
      this.tarefas = resposta;
    })
  }

  novoCadastro() {
    this.router.navigate(['/tarefa/form'])
  }

  preparaDelecao(tarefa: Tarefa) {
    this.tarefaSelecionada = tarefa;
  }

  deletar() {
    this.service.deleteById(this.tarefaSelecionada.id).subscribe(

      (response) => {
        this.menssageDeResposta = `Tarefa  ${this.tarefaSelecionada.nome} excluida com sucesso.`
        this.cor = 'success';
        this.ngOnInit();
      }, (errorResponse) => {
        this.menssageDeResposta = errorResponse.error.message;
        this.cor = 'danger';
      }
    )
  }

  inserirConvidado(idTarefa: number): void {
    this.router.navigate(['/tarefa/convidado/'])
    this.service.receber(idTarefa);

  }
}



