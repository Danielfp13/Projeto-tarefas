package com.api.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.tarefas.domain.Usuario;
import com.api.tarefas.repositories.UsuarioRepository;
import com.api.tarefas.security.UserSS;

@Service
public class UserDetailsServiceImp implements UserDetailsService
{

	@Autowired
	private UsuarioRepository repository;
	private Usuario usuario;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		usuario = repository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("Email ou senha incorretos."));
		return new UserSS(usuario.getId(),usuario.getEmail(), usuario.getSenha(), usuario.getPerfil());
	}

}