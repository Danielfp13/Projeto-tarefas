import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../service/tarefa.service';
import { Tarefa } from '../Tarefa';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../service/auth.service';
import { Usuario } from '../../login/Usuario';
import { FieldMessage } from '../FieldMessage';


@Component({
  selector: 'app-tarefa-form',
  templateUrl: './tarefa-form.component.html',
  styleUrls: ['./tarefa-form.component.css']
})
export class TarefaFormComponent implements OnInit {

  tarefa: Tarefa;
  tarefaId: any;
  usernameUsuario: string;
  usuarioAutenticado: Usuario;

  success: boolean = false;
  errors: FieldMessage[];
  erro: string;

  constructor(
    private service: TarefaService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService,
   
  ) {
    this.tarefa = new Tarefa();
    this.usuarioAutenticado = new Usuario()
  }

  ngOnInit(): void {
    this.usernameUsuario = this.authService.getUsuarioAutenticado()

    this.authService.buscarUsuario(this.usernameUsuario).subscribe(
      (response =>{
        this.usuarioAutenticado = response;
      })
    )

    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.tarefaId = urlParams['id'];
      if (this.tarefaId) {
        this.service.getTarefaById(this.tarefaId).subscribe(
          (response) => {
            this.tarefa = response;
          },
          (errorResponse) => {
            this.errors = errorResponse.error.erros
          }
        );
      }
    });
  }

  onSubmit() {
    if (this.tarefaId) {
      this.tarefa.dataHora=this.tarefa.dataHora.replace("T",' ');
      this.tarefa.idUsuario = this.usuarioAutenticado.id
      console.log(this.tarefa)
      this.service.alterarTarefa(this.tarefa, this.tarefaId).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];
        }, (errorResponse) => {
          this.success = false;
          this.errors = errorResponse.error.erros;
        })

    } else {
      this.tarefa.dataHora=this.tarefa.dataHora.replace("T",' ');
      this.tarefa.idUsuario = this.usuarioAutenticado.id
      console.log(this.tarefa)
      this.service.salvar(this.tarefa).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];
          this.tarefa = response;
        },
        (errorResponse) => {
          this.errors = errorResponse.error.erros;
          this.success = false;
        }
      );
    }
  }

  voltarParaListagem() {
    this.router.navigate(['/tarefa/lista']);
  }
}
