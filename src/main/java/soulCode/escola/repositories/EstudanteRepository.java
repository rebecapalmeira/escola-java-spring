package soulCode.escola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.escola.models.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Integer> {
	
	@Query(value="SELECT * FROM estudante WHERE id_turma = :id_turma", nativeQuery = true)
	List<Estudante> fetchByTurma(Integer id_turma);
	
	@Query(value="SELECT "
			+ "id_estudante, "
			+ "nome_estudante, "
			+ "cidade, "
			+ "nome_turma, "
			+ "descricao "
			+ "FROM turma RIGHT JOIN estudante "
			+ "ON estudante.id_turma = turma.id_turma "
			+ "ORDER BY estudante.nome_estudante", nativeQuery = true)
	List<List> buscarEstudantesComTurma();
	
}
