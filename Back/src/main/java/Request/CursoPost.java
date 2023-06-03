package Request;

public class CursoPost {
	
	private String descricao;
	private String qtdAlunos;
	private String dataInicio;
	private String dataFim;
	private Integer categoria;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getQtdAlunos() {
		return qtdAlunos;
	}
	public void setQtdAlunos(String qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;	
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
}
