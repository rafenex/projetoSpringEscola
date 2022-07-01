package com.escola.escola.dto;

import java.util.List;

import com.escola.escola.model.CTurma;

public class CursoDTO {
	private Long idCurso;
	private String nome;
	private String sigla;
	private List<CTurma> turmas;
	
	public CursoDTO() {
		// TODO Auto-generated constructor stub
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
	
	

}
