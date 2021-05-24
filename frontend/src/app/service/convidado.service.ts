import { Injectable } from '@angular/core';
import { Convidado } from '../tarefa/convidado/Convidado';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConvidadoService {
  constructor(private http: HttpClient) {}

  apiURL: string = environment.apiURLBase;

  salvar(convidado: Convidado): Observable<Convidado> {
    return this.http.post<Convidado>(`${this.apiURL}/convidados`,convidado);
  }
}
