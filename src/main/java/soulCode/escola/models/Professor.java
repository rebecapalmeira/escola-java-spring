package soulCode.escola.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_professor;

	@Column(nullable = false, length = 60)
	private String nome_professor;
	
	@Column(nullable = true, length = 80)
	private String formacao;
	
	@Column(nullable = true, length = 120)
	private String foto_professor;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_turma", unique = true)
	private Turma turma;

	
	//=======================================================
	//getters
	public Integer getId_professor() {
		return id_professor;
	}
	
	public String getNome_professor() {
		return nome_professor;
	}
	
	public String getFormacao() {
		return formacao;
	}

	public void setId_professor(Integer id_professor) {
		this.id_professor = id_professor;
	}
	
	public String getFoto_professor() {
		return foto_professor;
	}
	
	public Turma getTurma() {
		return turma;
	}

	
	//=====================================================
	//setters
	
	public void setNome_professor(String nome_professor) {
		this.nome_professor = nome_professor;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public void setFoto_professor(String foto_professor) {
		this.foto_professor = foto_professor;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
