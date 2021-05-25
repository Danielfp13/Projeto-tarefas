package com.api.tarefas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.tarefas.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

	@Query(value = "select * from tarefa where id_usuario = :id", nativeQuery = true)
	Page<Tarefa> findMyTask(@Param("id") Integer id, Pageable pageRequest);
}
