package soulCode.escola.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.escola.models.Estudante;
import soulCode.escola.models.Professor;
import soulCode.escola.models.Turma;
import soulCode.escola.services.ProfessorService;

@CrossOrigin
@RestController
@RequestMapping("/escola")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/professor")
	public List<Professor> buscarTodosProfessores() {
		List<Professor> professores = this.professorService.buscarTodosProfessores();
		return professores;
	}
	
	@GetMapping("/professor/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		Professor professor = professorService.buscarPorId(id);
		return ResponseEntity.ok().body(professor);
	}
	
	@GetMapping("/professor-turma")
	public List<List> buscarProfessoresComTurma() {
		@SuppressWarnings("rawtypes")
		List<List> professoresComTurma = professorService.buscarProfessoresComTurma();
		return professoresComTurma;
	}
	
	
	@PostMapping("/professor")
	public ResponseEntity<Professor> cadastrarProfessorComTurma(
			@Nullable @RequestParam(value="turma") Integer id_turma, 
			@RequestBody Professor professor) {
		
		professor = this.professorService.cadastrarProfessor(id_turma, professor);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(professor.getId_professor())
				.toUri();
		
		return ResponseEntity.created(uri).body(professor);
	}
	
	@PutMapping("/professor/{id}")
	public ResponseEntity<Professor> editarProfessor(
			@Nullable @RequestParam(value="turma") Turma turma,
			@RequestBody Professor professor,
			@PathVariable Integer id) {
		
		professor.setId_professor(id);
		professor.setTurma(turma);
		
		professorService.editarProfessor(professor);
		
		return ResponseEntity.noContent().build();
	}
	
	

}
