package com.escola.escola.dto;

import java.util.List;

import com.escola.escola.model.CAluno;
import com.escola.escola.model.CCurso;
import com.escola.escola.model.CProfessor;

public class TurmaDTO {
	private Long idTurma;
	private Integer semestre;
	private Integer ano;
	private List<CAluno> alunos;
	private CProfessor professor;
	private CCurso curso;
	
	public TurmaDTO() {
	}

	public TurmaDTO(Long idTurma, Integer semestre, Integer ano, List<CAluno> alunos, CProfessor professor,
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
	
	
	
	

}
