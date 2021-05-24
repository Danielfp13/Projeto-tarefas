package com.api.tarefas.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.tarefas.domain.Convidado;
import com.api.tarefas.dto.ConvidadoDTO;
import com.api.tarefas.service.ConvidadoService;

@RestController
@RequestMapping("/convidados")
public class ConvidadoResource {
	
	@Autowired
	private ConvidadoService service;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<Convidado> insert(@RequestBody ConvidadoDTO convidadoDto){
		Convidado convidado = service.fromDto(convidadoDto);
		convidado = service.insert(convidado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(convidado.getId()).toUri();
		return ResponseEntity.created(uri).body(convidado);
	}
}
