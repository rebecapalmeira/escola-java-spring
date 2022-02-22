package soulCode.escola.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Estudante;
import soulCode.escola.models.Turma;
import soulCode.escola.repositories.EstudanteRepository;
import soulCode.escola.services.exceptions.DataIntegrityViolationCustomException;
import soulCode.escola.services.exceptions.ObjectNotFoundCustomException;

@Service
public class EstudanteService {
	
	//fazendo a injeção de dependência EstudanteRepository para poder acessar os métodos disponibilizados pelo JpaRepository
	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Autowired
	private TurmaService turmaService;
	
	
	//==========================================================================================
	
	//listagem de todos os alunos cadastrados através do uso do método findAll() da dependencia estudanteRepository
	public List<Estudante> buscarTodosEstudantes() {
		return estudanteRepository.findAll();
	}
	
	//=======================================================================================
	//listagem de todos os estudantes junto com turma, query personalizada
	public List<List> buscarEstudantesComTurma() {
		return estudanteRepository.buscarEstudantesComTurma();	
	}
	
	//===================================================================================
	//método para fazer query personalizada de lista de alunos por turma
	public List<Estudante> buscarEstudantePorTurma(Integer id_turma) {
		List<Estudante> estudantesPorTurma =  estudanteRepository.fetchByTurma(id_turma);
		return estudantesPorTurma;
	}
	
	
	
	//==========================================================================================
	//Optional = evita o Erro NullPointerException (recurso não encontrado)
	//se o estudante for encontrado, retorna o estudante, se não, lança exceção
	public Estudante buscarEstudantePorId(Integer id) {
		Optional<Estudante> estudante = estudanteRepository.findById(id);
		return estudante.orElseThrow(() -> new ObjectNotFoundCustomException("Busca pelo ID informado não encontrou nenhum resultado."));
	}
	
	//==========================================================================================
	//método para cadastrar novo estudante
	public Estudante cadastrarEstudante(Integer id_turma, Estudante estudante) {
		estudante.setId_estudante(null);;
		Turma turma = turmaService.buscarTurmaPorId(id_turma);
		estudante.setTurma(turma);
		return estudanteRepository.save(estudante);
	}
	
	//==========================================================================================
	//método para deletar estudante
	public void deletarEstudante(Integer id) {
		try {
			estudanteRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationCustomException("Estudante não pode ser excluído pois está associado a outros dados.");
		}
		
	}
	
	//==========================================================================================
	//método para editar estudante
	public Estudante editarEstudante(Estudante estudante) {
		buscarEstudantePorId(estudante.getId_estudante());//busca estudante
		return estudanteRepository.save(estudante);//salva estudante com novas informações
	}
	

	

}
