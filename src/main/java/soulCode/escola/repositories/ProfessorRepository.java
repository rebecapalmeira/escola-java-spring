package soulCode.escola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.escola.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	@Query(value="SELECT "
			+ "professor.id_professor, "
			+ "nome_professor, "
			+ "formacao, "
			+ "nome_turma, "
			+ "descricao "
			+ "FROM professor LEFT JOIN turma "
			+ "ON professor.id_turma = turma.id_turma "
			+ "ORDER BY professor.nome_professor", nativeQuery = true)
	List<List> buscarProfessoresComTurma();
}
