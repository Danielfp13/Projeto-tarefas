package com.api.tarefas.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.tarefas.domain.Tarefa;
import com.api.tarefas.dto.TarefaDTO;
import com.api.tarefas.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaResource {

	@Autowired
	private TarefaService service;

	@PostMapping
	public ResponseEntity<Tarefa> insert(@Valid @RequestBody TarefaDTO tarefaDto) {
		Tarefa tarefa = service.fromDto(tarefaDto);
		tarefa = service.insert(tarefa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).body(tarefa);
	}

	@GetMapping
	public ResponseEntity<List<Tarefa>> findAll() {
		List<Tarefa> tarefa = service.findAll();
		return ResponseEntity.ok().body(tarefa);
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<Tarefa> find(@PathVariable Integer id) {
		Tarefa tarefa = service.find(id);
		return ResponseEntity.ok().body(tarefa);
	}


	@GetMapping(value = "/page")
	public ResponseEntity<Page<Tarefa>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
		Page<Tarefa> pageTarefa= service.findPage(page, linePerPage, direction, orderBy);
	
		return ResponseEntity.ok().body(pageTarefa);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody TarefaDTO tarefa, @PathVariable Integer id) {
		service.update(tarefa, id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/usuario")
	public ResponseEntity<Page<Tarefa>> myTask(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
		) {
		
		Page<Tarefa> tarefa = service.myTask(page, linePerPage, direction, orderBy);
		return ResponseEntity.ok().body(tarefa);
	}

}
