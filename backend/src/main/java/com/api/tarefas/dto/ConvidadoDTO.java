package com.api.tarefas.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvidadoDTO {
	
	@NotEmpty(message = "O campo nome é obrigatório.")
	private String nome;
	
	private Integer idTarefa;
	
}
