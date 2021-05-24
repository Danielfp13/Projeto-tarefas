import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { Usuario } from './Usuario';
import { FieldMessage } from '../model-error/FieldMessage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  usuario: Usuario;
  username: string;
  password: string;
  loginError: boolean;
  cadastrando: boolean;
  mensagem: string;
  errors: FieldMessage[] =[];
  erro: FieldMessage

  constructor(
    private router: Router,
    private service: AuthService

  ) { 
    this.usuario = new Usuario();
    this.erro =  new FieldMessage()
  }

  inserirUsuario(){
    this.service.insertUsuario(this.usuario).subscribe(
      response=>{
        this.mensagem = 'Cadastro realizado com sucesso! Efetue o login.'
        this.errors = [];
        this.cadastrando = false;
        this.usuario = new Usuario();
      },responseError=>{
        this.errors = responseError.error.erros;
        this.mensagem = "";
      }
    )
  }

  onSubmit(){
    this.service.tentarLogar(this.usuario.email, this.usuario.senha).subscribe(
      response => {
        const access_token = JSON.stringify(response);
        localStorage.setItem('access_token',access_token);
        this.router.navigate(['/tarefa']);
      },responseError =>{
        this.errors=[]
        this.erro.fieldName ="email ou senha"
        this.erro.message = 'Usuário e ou senha incorreto.'
        this.errors.push(this.erro)
      }
    ) 
  }

  preparaCadastrar(event: any){
    event.preventDefault();
    this.cadastrando = true;
    this.mensagem = '';
    this.errors = [];

  }

  cancelaCadastro(){
    this.cadastrando = false;
    this.errors = [];
    this.mensagem = '';
  }
}
