import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Usuario } from '../../login/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string
  usuarioAutenticado: Usuario
  constructor(private authService: AuthService, private router: Router) {
    this.usuarioAutenticado = new Usuario()
  }

  ngOnInit(): void {
    this.username = this.authService.getUsuarioAutenticado()
    this.authService.buscarUsuario(this.username).subscribe(
      response => {
        this.usuarioAutenticado = response
      }
    )
  }

  logout() {
    this.authService.enserrarSessao();
    this.router.navigate(['/login']);
  }
}
