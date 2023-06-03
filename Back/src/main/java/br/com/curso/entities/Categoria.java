package br.com.curso.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "tb_categoria")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;

	@NotNull
	private String categoria;

	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(Integer idCategoria, String categoria) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;

	}
	public Categoria(Integer idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
