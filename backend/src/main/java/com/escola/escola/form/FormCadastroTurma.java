package com.escola.escola.form;

import java.util.List;

public class FormCadastroTurma {
	private String semestre;
	private String ano;	
	private String idCurso;
	private String idProfessor;
	private List<Long> alunos;
	
	public FormCadastroTurma() {
		// TODO Auto-generated constructor stub
	}

	public List<Long> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Long> alunos) {
		this.alunos = alunos;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public String getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(String idProfessor) {
		this.idProfessor = idProfessor;
	}
	
	
	
	
	
}
