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

import com.escola.escola.dto.AlunoDTO;
import com.escola.escola.form.FormCadastroAluno;
import com.escola.escola.form.FormUpdateAluno;
import com.escola.escola.model.CAluno;
import com.escola.escola.repository.AlunoRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;

	@ApiOperation("Serviço cadastrar novo Aluno")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> cadastrar(@RequestBody FormCadastroAluno request) {
		try {
			CAluno aluno = new CAluno();
			aluno.setNome(request.getNome());
			aluno.setEndereco(request.getEndereco());
			alunoRepository.save(aluno);
			return ResponseEntity.status(HttpStatus.CREATED).body("Aluno cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}

	@ApiOperation("Serviço listar todos alunos")
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listar() {
		List<AlunoDTO> response = new ArrayList<AlunoDTO>();
		for (CAluno aluno : alunoRepository.findAll()) {
			AlunoDTO item = new AlunoDTO();
			item.setEndereco(aluno.getEndereco());
			item.setIdAluno(aluno.getIdAluno());
			item.setNome(aluno.getNome());
			item.setTurmas(aluno.getTurmas());
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation("Serviço para listar alunos por ID")
	@CrossOrigin
	@GetMapping("/{idAluno}")
	public ResponseEntity<?> findById(@PathVariable("idAluno") Long idAluno) {
		Optional<CAluno> item = alunoRepository.findById(idAluno);
		if (item.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
		} else {
			AlunoDTO response = new AlunoDTO();
			CAluno aluno = item.get();
			response.setIdAluno(aluno.getIdAluno());
			response.setNome(aluno.getNome());
			response.setEndereco(aluno.getEndereco());
			response.setTurmas(aluno.getTurmas());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@ApiOperation("Serviço para deletar aluno por ID")
	@CrossOrigin
	@DeleteMapping("{idAluno}")
	public ResponseEntity<String> deleteById(@PathVariable("idAluno") Long idAluno) {
		try {
			Optional<CAluno> item = alunoRepository.findById(idAluno);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
			} else {
				CAluno aluno = item.get();
				alunoRepository.delete(aluno);
				return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@ApiOperation("Serviço atualizar dados do aluno")
	@CrossOrigin
	@PutMapping("{idAluno}")
	public ResponseEntity<?> update(@PathVariable("idAluno") Long idAluno, @RequestBody FormUpdateAluno request) {
		try {
			Optional<CAluno> item = alunoRepository.findById(idAluno);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
			} else {
				CAluno aluno = item.get();
				aluno.setNome(request.getNome());
				aluno.setEndereco(request.getEndereco());
				alunoRepository.save(aluno);
				return ResponseEntity.status(HttpStatus.OK).body("Aluno atualizado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

}
