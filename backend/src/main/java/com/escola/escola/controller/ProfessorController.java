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

import com.escola.escola.dto.ProfessorDTO;
import com.escola.escola.form.FormCadastroProfessor;
import com.escola.escola.form.FormUpdateProfessor;
import com.escola.escola.model.CProfessor;
import com.escola.escola.repository.ProfessorRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@ApiOperation("Serviço para cadastrar professor")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> cadastrar(@RequestBody FormCadastroProfessor request) {
		try {
			CProfessor professor = new CProfessor();
			professor.setNome(request.getNome());
			professor.setEndereco(request.getEndereco());
			professorRepository.save(professor);
			return ResponseEntity.status(HttpStatus.CREATED).body("Professor cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para listar professores")
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> listar() {
		List<ProfessorDTO> response = new ArrayList<ProfessorDTO>();
		for (CProfessor professor : professorRepository.findAll()) {
			ProfessorDTO item = new ProfessorDTO();
			item.setEndereco(professor.getEndereco());
			item.setIdProfessor(professor.getIdProfessor());
			item.setNome(professor.getNome());
			item.setTurmas(professor.getTurmas());
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation("Serviço para listar professor por ID")
	@CrossOrigin
	@GetMapping("/{idProfessor}")
	public ResponseEntity<?> findById(@PathVariable("idProfessor") Long idProfessor) {
		Optional<CProfessor> item = professorRepository.findById(idProfessor);
		if (item.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
		} else {
			ProfessorDTO response = new ProfessorDTO();
			CProfessor professor = item.get();
			response.setIdProfessor(professor.getIdProfessor());
			response.setNome(professor.getNome());
			response.setEndereco(professor.getEndereco());
			response.setTurmas(professor.getTurmas());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
	
	@ApiOperation("Serviço para deletar professor")
	@CrossOrigin
	@DeleteMapping("{idProfessor}")
	public ResponseEntity<String> deleteById(@PathVariable("idProfessor") Long idProfessor) {
		try {
			Optional<CProfessor> item = professorRepository.findById(idProfessor);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
			} else {
				CProfessor professor = item.get();
				professorRepository.delete(professor);
				return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@ApiOperation("Serviço para atualizar professor")
	@CrossOrigin
	@PutMapping("{idProfessor}")
	public ResponseEntity<?> update(@PathVariable("idProfessor") Long idProfessor, @RequestBody FormUpdateProfessor request) {
		try {
			Optional<CProfessor> item = professorRepository.findById(idProfessor);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
			} else {
				CProfessor professor = item.get();
				professor.setNome(request.getNome());
				professor.setEndereco(request.getEndereco());
				professorRepository.save(professor);
				return ResponseEntity.status(HttpStatus.OK).body("Professor atualizado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}



}
