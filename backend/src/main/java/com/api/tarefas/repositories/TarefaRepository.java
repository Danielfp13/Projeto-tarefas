package com.api.tarefas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.tarefas.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

	@Query(value = "select * from tarefa where id_usuario = :id", nativeQuery = true)
	List<Tarefa> findMyTask(Integer id);

}
