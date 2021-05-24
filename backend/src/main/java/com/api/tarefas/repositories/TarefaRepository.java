package com.api.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tarefas.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
	
	

}
