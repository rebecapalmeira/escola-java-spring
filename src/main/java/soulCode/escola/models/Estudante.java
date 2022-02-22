package soulCode.escola.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estudante {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_estudante;
	
	@Column(nullable = false, length = 60)
	private String nome_estudante;
	
	@Column(nullable = true, length = 60)
	private String responsavel;
	
	@Column(nullable = false, length = 35)
	private String cidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;
	
	//getters
	public Integer getId_estudante() {
		return id_estudante;
	}
	
	public String getNome_estudante() {
		return nome_estudante;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public Turma getTurma() {
		return turma;
	}
	
	
	//setters
	public void setId_estudante(Integer id_estudante) {
		this.id_estudante = id_estudante;
	}

	public void setNome_estudante(String nome_estudante) {
		this.nome_estudante = nome_estudante;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
