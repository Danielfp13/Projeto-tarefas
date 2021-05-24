package com.api.tarefas.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaDTO {
	
	private Integer idUsuario;
		
	@NotEmpty(message = "O campo nome é obrigatório.")
	private String nome;
	
	@NotNull(message = "O campo data hora é obrigatório.")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date dataHora;
	
	@NotEmpty(message = "O campo duracao é obrigatório.")
	private String duracao; 
	
	@NotEmpty(message = "O campo local é obrigatório.")
	private String local;

	public TarefaDTO(Integer idUsuario,String nome,	 Date dataHora, String duracao, String local) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.dataHora = dataHora;
		this.duracao = duracao;
		this.local = local;
	}
	
	
}
