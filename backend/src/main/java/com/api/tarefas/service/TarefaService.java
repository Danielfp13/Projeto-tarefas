package com.api.tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.api.tarefas.domain.Tarefa;
import com.api.tarefas.domain.Usuario;
import com.api.tarefas.dto.TarefaDTO;
import com.api.tarefas.repositories.TarefaRepository;
import com.api.tarefas.repositories.UsuarioRepository;
import com.api.tarefas.service.exceptions.ObjectNotFoundException;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Tarefa insert(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}

	public Page<Tarefa> myTask(Integer page, Integer linesPerPage, String direction, String orderBy) {
		String user = UserService.authenticated();
		Usuario usuario = usuarioRepository.findByEmail(user)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado usuario autenticado"));
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return tarefaRepository.findMyTask(usuario.getId(), pageRequest);
	}

	public Tarefa find(Integer id) {
		return tarefaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado tarefas com id: " + id + "."));
	}

	public Page<Tarefa> findPage(Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return tarefaRepository.findAll(pageRequest);
	}

	public void delete(Integer id) {
		find(id);
		try {
			tarefaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não pode excluir tarefas com associações");
		}
	}

	public void update(TarefaDTO obj, Integer id) {
		Tarefa tarefa = find(id);
		tarefa.setNome(obj.getNome());
		tarefa.setLocal(obj.getLocal());
		tarefa.setDataHora(obj.getDataHora());
		tarefa.setDuracao(obj.getDuracao());
		Usuario usuario = usuarioRepository.findById(obj.getIdUsuario()).get();
		tarefa.setUsuario(usuario);
		tarefaRepository.save(tarefa);
	}

	public Tarefa fromDto(TarefaDTO tarefaDto) {
		Usuario usuario = usuarioRepository.findById(tarefaDto.getIdUsuario()).get();
		Tarefa tarefa = new Tarefa(null, tarefaDto.getNome(), tarefaDto.getDataHora(), tarefaDto.getDuracao(),
				tarefaDto.getLocal(), usuario);
		return tarefa;
	}
}
