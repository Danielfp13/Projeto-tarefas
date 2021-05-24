package com.api.tarefas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.tarefas.domain.Usuario;
import com.api.tarefas.dto.UsuarioDTO;
import com.api.tarefas.repositories.UsuarioRepository;
import com.api.tarefas.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsuarioRepository repository;

	public Usuario insert(UsuarioDTO usuario) {
		return repository.save(fromUsuario(usuario));
	}
	
	public Usuario fromUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario =  new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		return usuario;
	}

	public Usuario findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Não existe usuário com esse email" + email));
	}

	
	
}
