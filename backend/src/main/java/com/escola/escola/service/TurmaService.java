package com.escola.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.escola.model.CAluno;
import com.escola.escola.model.CTurma;
import com.escola.escola.repository.AlunoRepository;

@Service
public class TurmaService {
	@Autowired	
	private AlunoRepository alunoRepository;
	
	
	public void excluirAluno(Optional<CAluno> item, Optional<CTurma> turma) {		
		List<CAluno> alunos = turma.get().getAlunos();		
		
		for (CAluno aluno : alunoRepository.findAll()) {		
				if (item.get().getIdAluno() == aluno.getIdAluno()) {
					alunos.remove(aluno);			
				}					
			}				
	}

}
