package soulCode.escola.controllers;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.escola.models.Estudante;
import soulCode.escola.models.Turma;
import soulCode.escola.repositories.EstudanteRepository;
import soulCode.escola.services.EstudanteService;

@CrossOrigin()
@RestController
@RequestMapping("/escola")
public class EstudanteController {

	@Autowired
	private EstudanteService estudanteService;

	
	//=======================================================================
	//endpoint para buscar e retornar todos os estudantes
	@GetMapping("/estudante")
	public List<Estudante> buscarTodosEstudantes() {
		List<Estudante> estudantes = estudanteService.buscarTodosEstudantes();
		return estudantes;
	}
	
	//=========================================================================
	//endpoint para buscar estudantes com turma
	@GetMapping("/estudante-turma")
	public List<List> buscarEstudantesComTurma() {
		@SuppressWarnings("rawtypes")
		List<List> estudantesComTurma = estudanteService.buscarEstudantesComTurma();
		System.out.println(estudantesComTurma);
		return estudantesComTurma;
	}
	
	//=======================================================================
	//endpoint para buscar e retornar somente um estudante (pelo ID)
	
	//ResponseEntity retorna os dados de um registro do BD(corpo), junto com cabeçalho, status
	//@PathVariable - especifica que o ID procurado vai ser passado através da url
	
	@GetMapping("/estudante/{id}")
	public ResponseEntity<?> buscarEstudantePorId(@PathVariable Integer id) {
		Estudante estudante = estudanteService.buscarEstudantePorId(id);
		return ResponseEntity.ok().body(estudante);
	}
	
	//======================================================================================
	@GetMapping("/estudante/busca-turma/{id_turma}")
	public List<Estudante> buscarEstudantePorTurma(@PathVariable Integer id_turma) {
		List<Estudante> estudantesPorTurma = estudanteService.buscarEstudantePorTurma(id_turma);
		return estudantesPorTurma;
	}

	
	//===========================================================================
	//endpoint para criar recurso
	//URI - identificador uniforme de recurso - identifica um recurso vindo do servidor (http + localização do recurso (url) + nome do recurso)
	//ServletUriComponentBuilder - classe uqe possui métodos para criar ligações com o Servidor
	
//	@ResponseStatus(HttpStatus.CREATED)
//	@PostMapping("estudante")
//	public ResponseEntity<Void> cadastrarEstudante(@RequestBody Estudante estudante) {
//		estudante = estudanteService.cadastrarEstudante(estudante);
//		
//		URI uri = ServletUriComponentsBuilder
//				.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(estudante.getId())
//				.toUri();
//		
//		return ResponseEntity.created(uri).build();
//	}
	
	@PostMapping("/estudante")
	public ResponseEntity<Estudante> cadastrarEstudante(
			@RequestParam(value="turma") Integer id_turma,
			@RequestBody Estudante estudante) {
				
		estudante = estudanteService.cadastrarEstudante(id_turma, estudante);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estudante.getId_estudante())
				.toUri();
		
		return ResponseEntity.created(uri).body(estudante);
		
	}
	
	//=========================================================================
	//endpoint para deletar estudante
	
	@DeleteMapping("/estudante/{id}")
	public ResponseEntity<Void> deletarEstudante(@PathVariable Integer id) {
		estudanteService.deletarEstudante(id);
		return ResponseEntity.noContent().build();
	}
	
	//==========================================================================================
	//endpoint apra editar cadastro de estudante
	
//	@PutMapping("estudante/{id}")
//	public ResponseEntity<Void> editarEstudante(@PathVariable Integer id, @RequestBody Estudante estudante) {
//		estudante.setId(id);
//		estudante = estudanteService.editarEstudante(estudante);
//		
//		return ResponseEntity.noContent().build();
//	}
	
	@PutMapping("estudante/{id}")
	public ResponseEntity<Void> editarEstudante(
			@RequestParam(value = "turma") Turma turma, 
			@PathVariable Integer id, 
			@RequestBody Estudante estudante) {
		
		estudante.setId_estudante(id);
		estudante.setTurma(turma);
		estudante = estudanteService.editarEstudante(estudante);
		
		return ResponseEntity.noContent().build();
	}

}
