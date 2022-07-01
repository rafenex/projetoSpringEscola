package com.escola.escola.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CCurso {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long idCurso;
	private String nome;
	private String sigla;
	

	@JsonIgnore
	@OneToMany(mappedBy = "curso")
	private List<CTurma> turmas;

	public CCurso() {
		super();
	}

	public CCurso(Long idCurso, String nome, String sigla, List<CTurma> turmas) {
		super();
		this.idCurso = idCurso;
		this.nome = nome;
		this.sigla = sigla;
		this.turmas = turmas;
	}


	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<CTurma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<CTurma> turmas) {
		this.turmas = turmas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCurso other = (CCurso) obj;
		return Objects.equals(idCurso, other.idCurso);
	}

	@Override
	public String toString() {
		return "CCurso [idCurso=" + idCurso + ", nome=" + nome + ", sigla=" + sigla + ", turmas=" + turmas + "]";
	}

	
	
	
	
	

}
