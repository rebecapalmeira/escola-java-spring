package soulCode.escola.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Estudante;
import soulCode.escola.models.Professor;
import soulCode.escola.models.Turma;
import soulCode.escola.repositories.ProfessorRepository;
import soulCode.escola.services.exceptions.ObjectNotFoundCustomException;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TurmaService turmaService;
	
	//===========================================================
	
	public List<Professor> buscarTodosProfessores() {
		List<Professor> professores = professorRepository.findAll();
		return professores;
	}
	
	public Professor buscarPorId(Integer id) {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundCustomException("Busca pelo ID informado não encontrou nenhum resultado."));
	}
	
	public List<List> buscarProfessoresComTurma() {
		return professorRepository.buscarProfessoresComTurma();	
	}
	
	public Professor cadastrarProfessorSemTurma(Professor professor) {
		professor.setId_professor(null);
		return this.professorRepository.save(professor);	
	}
	
	public Professor cadastrarProfessor(Integer id_turma, Professor professor) {
		if(id_turma != null) {
			professor.setId_professor(null);
			Turma turma = this.turmaService.buscarTurmaPorId(id_turma);
			professor.setTurma(turma);
		} 
		return professorRepository.save(professor);
	}
	
	public Professor editarProfessor(Professor professor) {
		buscarPorId(professor.getId_professor());
		return professorRepository.save(professor);
	}
	
	
}
