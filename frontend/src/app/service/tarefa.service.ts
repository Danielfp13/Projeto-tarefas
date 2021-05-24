import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Tarefa } from '../tarefa/Tarefa';

@Injectable({
  providedIn: 'root',
})
export class TarefaService {

  idTarefa: number

  constructor(private http: HttpClient) {}

  apiURL: string = environment.apiURLBase;

  salvar(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>(`${this.apiURL}/tarefas`,tarefa);
  }

  listaTarefa(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(`${this.apiURL}/tarefas`);
  }

  listaMinhaTarefa(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(`${this.apiURL}/tarefas/usuario`);
  }

  alterarTarefa(tarefa: Tarefa, id: any):Observable<any>{
    return this.http.put<Tarefa>(`${this.apiURL}/tarefas/${id}`,tarefa);
  }

  getTarefaById(id: any): Observable<Tarefa>{
    return this.http.get<Tarefa>(`${this.apiURL}/tarefas/${id}`);
  }

  deleteById(id: any) : Observable<any>{
    return this.http.delete<any>(`${this.apiURL}/tarefas/${id}`);
  }

  receber(id: number): void{
    this.idTarefa = id;
  }

  enviar():number{
    return this.idTarefa
  }
}
