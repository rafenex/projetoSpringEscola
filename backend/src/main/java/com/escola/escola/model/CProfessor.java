package com.escola.escola.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //HttpMessageConversionException:
@Entity
public class CProfessor {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long idProfessor;
	private String nome;
	private String endereco;
	
	@JsonIgnore //turma não aparece em requisições que não for /professor 
	@OneToMany(mappedBy = "professor")
	private List<CTurma> turmas = new ArrayList<>();

	public CProfessor() {
		super();
	}
	

	public CProfessor(Long idProfessor, String nome, String endereco, List<CTurma> turmas) {
		super();
		this.idProfessor = idProfessor;
		this.nome = nome;
		this.endereco = endereco;
		this.turmas = turmas;
	}
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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



	public List<CTurma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<CTurma> turmas) {
		this.turmas = turmas;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idProfessor);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CProfessor other = (CProfessor) obj;
		return Objects.equals(idProfessor, other.idProfessor);
	}


	


	

	
	
	
	

}
