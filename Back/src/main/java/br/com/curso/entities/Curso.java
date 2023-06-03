package br.com.curso.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_curso")
@Audited
@AuditTable("TB_CURSO_LOG")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCurso;

	@NotNull
	private String descricao;

	private Integer qtdAlunos;
	
	@NotNull
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	
	@NotNull
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate dataFim;

	@ManyToOne
	@JoinColumn(name = "FkCategoria")
	@JsonIgnoreProperties("curso")
	private Categoria categoria;

	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Curso(long idCurso, String descricao, Integer qtdAlunos, LocalDate dataInicio, LocalDate dataFim,
			Categoria categoria) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.qtdAlunos = qtdAlunos;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.categoria = categoria;
	}

	public long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(long idCurso) {
		this.idCurso = idCurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdAlunos() {
		return qtdAlunos;
	}

	public void setQtdAlunos(Integer qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
