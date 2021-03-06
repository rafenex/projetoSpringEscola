package com.escola.escola.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.escola.escola.dto.TurmaDTO;
import com.escola.escola.form.FormCadastroTurma;
import com.escola.escola.form.FormUpdateTurma;
import com.escola.escola.model.CAluno;
import com.escola.escola.model.CTurma;
import com.escola.escola.repository.AlunoRepository;
import com.escola.escola.repository.CursoRepository;
import com.escola.escola.repository.ProfessorRepository;
import com.escola.escola.repository.TurmaRepository;
import com.escola.escola.service.TurmaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TurmaService turmaService;

	@GetMapping
	@ApiOperation("Serviço para listar turmas com seu professor, alunos e curso")
	@CrossOrigin
	public ResponseEntity<List<TurmaDTO>> listarTudo() {
		List<TurmaDTO> response = new ArrayList<TurmaDTO>();
		for (CTurma turma : turmaRepository.findAll()) {
			TurmaDTO item = new TurmaDTO();
			item.setIdTurma(turma.getIdTurma());
			item.setAno(turma.getAno());
			item.setSemestre(turma.getSemestre());
			item.setProfessor(turma.getProfessor());
			item.setAlunos(turma.getAlunos());
			item.setCurso(turma.getCurso());

			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation("Serviço para listar turma por ID")
	@CrossOrigin
	@GetMapping("/{idTurma}")
	public ResponseEntity<?> listarPorId(@PathVariable("idTurma") Long idTurma) {
		Optional<CTurma> item = turmaRepository.findById(idTurma);
		if (item.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
		} else {
			TurmaDTO response = new TurmaDTO();
			CTurma turma = item.get();
			response.setIdTurma(turma.getIdTurma());
			response.setAno(turma.getAno());
			response.setSemestre(turma.getSemestre());
			response.setProfessor(turma.getProfessor());
			response.setAlunos(turma.getAlunos());
			response.setCurso(turma.getCurso());

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}
	}


	@ApiOperation("Serviço para deletar turma por ID")
	@CrossOrigin
	@DeleteMapping("/{idTurma}")
	public ResponseEntity<?> deletarPorId(@PathVariable("idTurma") Long idTurma) {
		try {
			Optional<CTurma> item = turmaRepository.findById(idTurma);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado");
			} else {
				CTurma turma = item.get();
				turmaRepository.delete(turma);
				return ResponseEntity.status(HttpStatus.OK).body("Turma deletada com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}


	@SuppressWarnings("deprecation")
	@ApiOperation("Serviço para atualizar turma")
	@CrossOrigin
	@PutMapping("/{idTurma}")
	public ResponseEntity<?> update(@PathVariable("idTurma")Long idTurma, @RequestBody FormUpdateTurma request){
		try {
			Optional<CTurma> item = turmaRepository.findById(idTurma);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
			}else {
				CTurma turma = item.get();				
				turma.setSemestre(request.getSemestre());
				turma.setAno(request.getAno());
				turma.setCurso(cursoRepository.getById(request.getIdCurso()));
				turma.setProfessor(professorRepository.getById(request.getIdProfessor()));
				turmaRepository.save(turma);
				return ResponseEntity.status(HttpStatus.OK).body("Turma atualizada");
				}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+ e.getMessage());
		}					
	}	
	
	@SuppressWarnings("deprecation")
	@ApiOperation("Serviço para cadastrar turma")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String>cadastrar(@RequestBody FormCadastroTurma request){
		try {
			CTurma turma = new CTurma();
			turma.setAno(Integer.parseInt(request.getAno()));
			turma.setSemestre(Integer.parseInt(request.getSemestre()));
			turma.setCurso(cursoRepository.getById(Long.parseLong(request.getIdCurso())));
			turma.setProfessor(professorRepository.getById(Long.parseLong(request.getIdProfessor())));
			turma.setAlunos(alunoRepository.findAllById((request.getAlunos())));
			if (turma.getAlunos().isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Adicione aluno à turma");
			}
			if (turma.getAlunos().size() > 40) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Número de alunos maior que o permitido");
			}
			turmaRepository.save(turma);
			return ResponseEntity.status(HttpStatus.CREATED).body("Turma cadastrada com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}		
	}
	
	@ApiOperation("Serviço para tirar um aluno da turma")
	@CrossOrigin
	@GetMapping("/delAluno/{idTurma}/{idAluno}")
	public ResponseEntity<?>excluirAluno(@PathVariable("idTurma")Long idTurma,
											 @PathVariable("idAluno")Long idAluno){
		try {
			Optional<CAluno> itemA = alunoRepository.findById(idAluno);
			Optional<CTurma> item = turmaRepository.findById(idTurma);
			if(item.isEmpty() || itemA.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou aluno não encontrado");
			} else {				
					CTurma turma = item.get();
					CAluno aluno = itemA.get();
					if (!turma.getAlunos().contains(aluno)) {
						return ResponseEntity.status(HttpStatus.CONFLICT).body("Aluno não está na turma");	
					}
					turmaService.excluirAluno(itemA, item);
					turmaRepository.save(turma);	
					return ResponseEntity.status(HttpStatus.OK).body("Aluno exluido da turma!");										
			}			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

			
		}
	
	@ApiOperation("Serviço para adicionar um aluno a turma")
	@CrossOrigin
	@GetMapping("/addAluno/{idTurma}/{idAluno}")
	public ResponseEntity<?>adicionarAluno(@PathVariable("idTurma")Long idTurma, 
								  	       @PathVariable("idAluno")Long idAluno){
		try {
			Optional<CTurma> item = turmaRepository.findById(idTurma);
			Optional<CAluno> itemA = alunoRepository.findById(idAluno);
			if(item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
			} else {
				CTurma turma = item.get();
				CAluno aluno = itemA.get();
				if (turma.getAlunos().contains(aluno)) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("Aluno ja está na turma");	
				}
				if (turma.getAlunos().size()>=40) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("A turma está cheia");	
				}
				
				turma.getAlunos().add(aluno);
				turmaRepository.save(turma);
				return ResponseEntity.status(HttpStatus.OK).body("Aluno adicionado a turma");				
			}			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Aluno não encontrado");
		}
	
}

	
	



	
	

}

