package com.escola.escola.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CTurma {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTurma;
	private Integer semestre;
	private Integer ano;
	
	@JsonIgnore
	@ManyToMany
	@NotNull
	private List<CAluno> alunos;
	

	@JsonIgnore
	@ManyToOne
	@NotNull
	private CProfessor professor;
	
	@JsonIgnore
	@ManyToOne
	@NotNull
	private CCurso curso;
	
	
	public CTurma() {
	}

	public CTurma(Long idTurma, Integer semestre, Integer ano, List<CAluno> alunos, CProfessor professor,
			CCurso curso) {
		super();
		this.idTurma = idTurma;
		this.semestre = semestre;
		this.ano = ano;
		this.alunos = alunos;
		this.professor = professor;
		this.curso = curso;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<CAluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<CAluno> alunos) {
		this.alunos = alunos;
	}

	public CProfessor getProfessor() {
		return professor;
	}

	public void setProfessor(CProfessor professor) {
		this.professor = professor;
	}

	public CCurso getCurso() {
		return curso;
	}

	public void setCurso(CCurso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTurma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTurma other = (CTurma) obj;
		return Objects.equals(idTurma, other.idTurma);
	}

	@Override
	public String toString() {
		return "CTurma [idTurma=" + idTurma + ", semestre=" + semestre + ", ano=" + ano + ", alunos=" + alunos
				+ ", professor=" + professor + ", curso=" + curso + "]";
	}

	

	
	
	

}
