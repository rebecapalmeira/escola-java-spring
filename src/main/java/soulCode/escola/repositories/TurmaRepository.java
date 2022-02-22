package soulCode.escola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.escola.models.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
	
	@Query(value="SELECT "
			+ "turma.id_turma, "
			+ "nome_turma, "
			+ "descricao, "
			+ "nome_professor "
			+ "FROM turma LEFT JOIN professor "
			+ "ON  turma.id_turma = professor.id_turma "
			+ "ORDER BY turma.nome_turma", nativeQuery = true)
	List<List> buscarTurmasComProfessor();

}
