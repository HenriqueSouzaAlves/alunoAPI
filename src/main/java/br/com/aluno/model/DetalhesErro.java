package br.com.aluno.model;

public class DetalhesErro {
	
	private String titulo;
	
	private Long Status;
	
	private String dataHora;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getStatus() {
		return Status;
	}

	public void setStatus(Long status) {
		Status = status;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

}
