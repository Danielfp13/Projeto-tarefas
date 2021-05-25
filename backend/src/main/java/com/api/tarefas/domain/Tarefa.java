package com.api.tarefas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tarefa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataHora;
	
	private String duracao; 
	
	private String local;
	
	@OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL)
	private List<Convidado> convidados = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Tarefa(Integer id, String nome, Date dataHora, String duracao, String local, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataHora = dataHora;
		this.duracao = duracao;
		this.local = local;
		this.usuario = usuario;
	}
}
