package soulCode.escola.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Turma;
import soulCode.escola.repositories.TurmaRepository;
import soulCode.escola.services.exceptions.DataIntegrityViolationCustomException;
import soulCode.escola.services.exceptions.ObjectNotFoundCustomException;


@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
	
	//busca e retorna todas as turmas
	public List<Turma> mostrarTodasTurmas() {
		return turmaRepository.findAll();
	}
	
	//busca e retorna apenas uma turma
	public Turma buscarTurmaPorId(Integer id) {
		Optional<Turma> turma = turmaRepository.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundCustomException("Busca pelo ID informado não encontrou nenhum registro."));
	}
	
	public List<List> buscarTurmasComProfessor() {
		return turmaRepository.buscarTurmasComProfessor();	
	}
	
	//cadastrar nova turma
	public Turma cadastrarTurma(Turma turma) {
		turma.setId_turma(null);;//para garantir que o id não é setado por aqui
		return turmaRepository.save(turma);
	}
	
	//editar turma
	public Turma editarTurma(Turma turma) {
		buscarTurmaPorId(turma.getId_turma());
		return turmaRepository.save(turma);	 
	}
	
	//deletar uma turma
	public void deletarTurmaPorId(Integer id) {
		try {
			turmaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationCustomException("A turma não pode ser deletada em razão de possuir estudantes associados a ela.");
		}
		
	}
	
}
