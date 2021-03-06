package com.escola.escola.dto;

import java.util.List;

import com.escola.escola.model.CTurma;

public class AlunoDTO {
	private Long idAluno;
	private String nome;
	private String endereco;
	private List<CTurma> turmas;

	
	public AlunoDTO() {
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
	
	



}
