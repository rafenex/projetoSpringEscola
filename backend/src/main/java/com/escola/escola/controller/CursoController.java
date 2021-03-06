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

import com.escola.escola.dto.CursoDTO;
import com.escola.escola.form.FormCadastroCurso;
import com.escola.escola.form.FormUpdateCurso;
import com.escola.escola.model.CCurso;
import com.escola.escola.repository.CursoRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

	@Autowired
	CursoRepository cursoRepository;
	
	@ApiOperation("Serviço para cadastrar novo curso")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> cadastrar(@RequestBody FormCadastroCurso request) {
		try {
			CCurso curso = new CCurso();
			curso.setNome(request.getNome());
			curso.setSigla(request.getSigla());
			cursoRepository.save(curso);
			return ResponseEntity.status(HttpStatus.CREATED).body("Curso cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para listar todos cursos")
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CursoDTO>> listar() {
		List<CursoDTO> response = new ArrayList<CursoDTO>();
		for (CCurso curso : cursoRepository.findAll()) {
			CursoDTO item = new CursoDTO();
			item.setSigla(curso.getSigla());
			item.setIdCurso(curso.getIdCurso());
			item.setNome(curso.getNome());
			item.setTurmas(curso.getTurmas());
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation("Serviço para listar cursos por ID")
	@CrossOrigin
	@GetMapping("/{idCurso}")
	public ResponseEntity<?> findById(@PathVariable("idCurso") Long idCurso) {
		Optional<CCurso> item = cursoRepository.findById(idCurso);
		if (item.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
		} else {
			CursoDTO response = new CursoDTO();
			CCurso curso = item.get();
			response.setIdCurso(curso.getIdCurso());
			response.setNome(curso.getNome());
			response.setSigla(curso.getSigla());
			response.setTurmas(curso.getTurmas());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
	
	@ApiOperation("Serviço para deletar curso por ID")
	@CrossOrigin
	@DeleteMapping("{idCurso}")
	public ResponseEntity<String> deleteById(@PathVariable("idCurso") Long idCurso) {
		try {
			Optional<CCurso> item = cursoRepository.findById(idCurso);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
			} else {
				CCurso curso = item.get();
				cursoRepository.delete(curso);
				return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para atualizar curso ID")
	@CrossOrigin
	@PutMapping("{idCurso}")
	public ResponseEntity<?> update(@PathVariable("idCurso") Long idCurso, @RequestBody FormUpdateCurso request) {
		try {
			Optional<CCurso> item = cursoRepository.findById(idCurso);
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
			} else {
				CCurso curso = item.get();
				curso.setNome(request.getNome());
				cursoRepository.save(curso);
				return ResponseEntity.status(HttpStatus.OK).body("Curso atualizado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}



}
