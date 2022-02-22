package soulCode.escola.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.escola.models.Turma;
import soulCode.escola.services.TurmaService;

@CrossOrigin()
@RestController
@RequestMapping("/escola")
public class TurmaController {

	@Autowired
	TurmaService turmaService;
	
	//endpoint de busca de todas as turmas
	@GetMapping("/turma")
	public List<Turma> mostrarTodasTurmas() {
		List<Turma> turmas = turmaService.mostrarTodasTurmas();
		return turmas;
	}
	
	//endpoint para buscar uma turma por ID
	@GetMapping("/turma/{id}")
	public ResponseEntity<?> buscarTurmaPorId(@PathVariable Integer id) {
		Turma turma = turmaService.buscarTurmaPorId(id);
		
		return ResponseEntity.ok().body(turma);
	}
	
	@GetMapping("/turma-professor")
	public List<List> buscarTurmasComProfessor() {
		@SuppressWarnings("rawtypes")
		List<List> turmasComProfessor = turmaService.buscarTurmasComProfessor();
		return turmasComProfessor;
	}
	
	//endpoint para cadastrar nova turma
	@PostMapping("/turma")
	public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma turma) {
		turma = turmaService.cadastrarTurma(turma);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(turma.getId_turma()).
				toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	//endpoint para editar turma
	@PutMapping("/turma/{id}")
	public ResponseEntity<Turma> editarTurma(@PathVariable Integer id, @RequestBody Turma turma) {
		turma.setId_turma(id);
		turma = turmaService.editarTurma(turma);
		
		return ResponseEntity.noContent().build();
	}
	
	//endpoint para deletar turma
	@DeleteMapping("/turma/{id}")
	public ResponseEntity<Void> deletarTurmaPorId(@PathVariable Integer id) {
		turmaService.deletarTurmaPorId(id);
		
		return ResponseEntity.noContent().build();
	}
}
