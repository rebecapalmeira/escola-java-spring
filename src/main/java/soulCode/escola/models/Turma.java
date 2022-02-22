package soulCode.escola.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_turma;
	
	@Column(nullable = false, length = 10)
	private String nome_turma;
	
	@Column(nullable = false, length = 40)
	private String descricao;
	
	@OneToMany(mappedBy = "turma")
	private List<Estudante> estudantes = new ArrayList<>();


	@OneToOne
	@JoinColumn(name = "id_professor", unique = true)
	private Professor professor;

	
	//getters
	public Integer getId_turma() {
		return id_turma;
	}
	
	public String getNome_turma() {
		return nome_turma;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public List<Estudante> getEstudantes() {
		return estudantes;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	
	//setters
	public void setId_turma(Integer id_turma) {
		this.id_turma = id_turma;
	}

	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
