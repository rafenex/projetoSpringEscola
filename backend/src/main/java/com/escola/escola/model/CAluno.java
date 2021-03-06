package com.escola.escola.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class CAluno {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAluno;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "alunos") //lista de turma é mapeado para alunos
	private List<CTurma> turmas;
	
	public CAluno() {
	}
	

	public CAluno(Long idAluno, String nome, String endereco, List<CTurma> turmas) {
		super();
		this.idAluno = idAluno;
		this.nome = nome;
		this.endereco = endereco;
		this.turmas = turmas;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<CTurma> getTurmas() {
		return turmas;
	}
	

	public void setTurmas(List<CTurma> turmas) {
		this.turmas = turmas;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idAluno);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CAluno other = (CAluno) obj;
		return Objects.equals(idAluno, other.idAluno);
	}


	@Override
	public String toString() {
		return "CAluno [idAluno=" + idAluno + ", nome=" + nome + ", endereco=" + endereco + ", turmas=" + turmas + "]";
	}




	
	
	
}
