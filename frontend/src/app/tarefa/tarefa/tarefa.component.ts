import { Component, EventEmitter, OnInit } from '@angular/core';
import { Tarefa } from '../Tarefa';
import { TarefaService } from '../../service/tarefa.service';
import { Router } from '@angular/router';
import { PageEvent, MatPaginatorIntl } from '@angular/material/paginator';
import { Convidado } from '../convidado/Convidado';


@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  tarefas: any[] = [];
  convidados: Convidado[] = []
  tarefaSelecionada: Tarefa;
  menssageDeResposta: string;
  cor: string = 'denger';
  idtarefa: number;

  page: number = 0;
  linePerPage: number = 5;
  direction: string = 'ASC';
  orderBy: string = 'id';
  totalElementos: number = 0;
  totalPage: number = 0;

  pageSizeOptions: number[] = [5, 10, 15, 20];

  colunas = ['id', 'nome', 'data', 'duracao', 'local', 'convidados'];

  constructor(private service: TarefaService,
    private router: Router) {

  }


  findPage(page: number, linePerPage: number, direction: string, orderBy: string) {
    this.service.findPage(page, linePerPage, direction, orderBy).subscribe(
      response => {
        this.tarefas = response.content
        this.totalElementos = response.totalElements
        this.totalPage = response.totalPages
        this.page = response.number
      }, responseError => {
        this.menssageDeResposta = responseError.error.message;
      }
    );
  }

  pageEvent: PageEvent;

  paginar(event: PageEvent) {
    this.page = event.pageIndex
    this.linePerPage = event.pageSize
    this.findPage(this.page, this.linePerPage, this.direction, this.orderBy);
  }

  ngOnInit(): void {
    this.findPage(this.page, this.linePerPage, this.direction, this.orderBy);
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
    this.router.navigate([
      '/tarefa/convidado/'])
    this.service.idTarefa=idTarefa;
  }

}



