package com.api.tarefas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.api.tarefas.domain.Usuario;
import com.api.tarefas.service.validation.EmailDupicado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	@NotEmpty(message = "O campo nome é obrigatório.")
	private String nome;

	@Email(message = "O campo e-mail está incorreto.")
	@EmailDupicado
	@NotEmpty(message = "O campo e-mail é obrigatório.")
	private String email;

	@NotEmpty(message = "O campo senha é obrigatório.")
	private String senha;

	public UsuarioDTO(Usuario usuario) {
	
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}
}
