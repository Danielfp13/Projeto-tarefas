package com.api.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tarefas.domain.Convidado;
import com.api.tarefas.domain.Tarefa;
import com.api.tarefas.dto.ConvidadoDTO;
import com.api.tarefas.repositories.ConvidadoRepository;
import com.api.tarefas.repositories.TarefaRepository;
import com.api.tarefas.service.exceptions.ObjectNotFoundException;

@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;

	public Convidado insert(Convidado convidado) {
		return convidadoRepository.save(convidado);
	}

	public Convidado fromDto(ConvidadoDTO convidadoDto) {
		Tarefa tarefa = tarefaRepository.findById(convidadoDto.getIdTarefa()).orElseThrow( () -> new ObjectNotFoundException("Não existe tarefa com id =" + convidadoDto.getIdTarefa()));
		Convidado convidado = new Convidado(null, convidadoDto.getNome(), tarefa);
		return convidado;
	}
		
}
