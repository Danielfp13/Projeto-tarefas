package com.api.tarefas.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.tarefas.domain.Usuario;
import com.api.tarefas.dto.UsuarioDTO;
import com.api.tarefas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioDTO usuarioDto){
		
		Usuario usuario = service.insert(usuarioDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@GetMapping
	public ResponseEntity<Usuario> find(@RequestParam(value = "email", defaultValue = "" , required = false) String email) {
		Usuario usuario = service.findByEmail(email);
		return ResponseEntity.ok().body(usuario);
	}

}
