package com.escola.escola.dto;

import java.util.List;

import com.escola.escola.model.CTurma;

public class ProfessorDTO {
	private Long idProfessor;
	private String nome;
	private String endereco;
	private List<CTurma> turmas;
	
	public ProfessorDTO() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
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
	
	
}
