
package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChamadoVO {
	private int idChamado;
	private String titulo;
	private String descricao;
	private LocalDate dataHoraAbertura;
	private LocalDate dataHoraFechamento;
	private int idUsuario;
	private String solucao;
	private int idTecnico;
	@Override
	public String toString() {
		return "ChamadoVO [idChamado=" + idChamado + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", dataHoraAbertura=" + dataHoraAbertura + ", dataHoraFechamento=" + dataHoraFechamento
				+ ", idUsuario=" + idUsuario + ", solucao=" + solucao + ", idTecnico=" + idTecnico + "]";
	}
	public ChamadoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChamadoVO(int idChamado, String titulo, String descricao, LocalDate dataHoraAbertura,
			LocalDate dataHoraFechamento, int idUsuario, String solucao, int idTecnico) {
		super();
		this.idChamado = idChamado;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHoraAbertura = dataHoraAbertura;
		this.dataHoraFechamento = dataHoraFechamento;
		this.idUsuario = idUsuario;
		this.solucao = solucao;
		this.idTecnico = idTecnico;
	}
	public int getIdChamado() {
		return idChamado;
	}
	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataHoraAbertura() {
		return dataHoraAbertura;
	}
	public void setDataHoraAbertura(LocalDate dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}
	public LocalDate getDataHoraFechamento() {
		return dataHoraFechamento;
	}
	public void setDataHoraFechamento(LocalDate dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getSolucao() {
		return solucao;
	}
	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}
	public int getIdTecnico() {
		return idTecnico;
	}
	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}
}