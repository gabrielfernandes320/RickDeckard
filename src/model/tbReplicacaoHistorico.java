package model;

import java.sql.Timestamp;

public class tbReplicacaoHistorico {
	
	public tbReplicacaoHistorico(){		
		super();
	}
	
	public int getCodigo_execucao() {
		return codigo_execucao;
	}
	public void setCodigo_execucao(int codigo_execucao) {
		this.codigo_execucao = codigo_execucao;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getProcesso() {
		return processo;
	}
	public void setProcesso(String processo) {
		this.processo = processo;
	}
	public String getDatabase_origem() {
		return database_origem;
	}
	public void setDatabase_origem(String database_origem) {
		this.database_origem = database_origem;
	}
	public String getDatabase_destino() {
		return database_destino;
	}
	public void setDatabase_destino(String database_destino) {
		this.database_destino = database_destino;
	}
	public String getUsuario_origem() {
		return usuario_origem;
	}
	public void setUsuario_origem(String usuario_origem) {
		this.usuario_origem = usuario_origem;
	}
	public String getUsuario_destino() {
		return usuario_destino;
	}
	public void setUsuario_destino(String usuario_destino) {
		this.usuario_destino = usuario_destino;
	}
	public Timestamp getInicio_data_hora() {
		return inicio_data_hora;
	}
	public void setInicio_data_hora(Timestamp inicio_data_hora) {
		this.inicio_data_hora = inicio_data_hora;
	}
	public Timestamp getFim_data_hora() {
		return fim_data_hora;
	}
	public void setFim_data_hora(Timestamp fim_data_hora) {
		this.fim_data_hora = fim_data_hora;
	}
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	private int codigo_execucao;
	private String usuario;
	private String processo;
	private String database_origem;
	private String database_destino;
	private String usuario_origem;
	private String usuario_destino;
	private Timestamp inicio_data_hora;
	private Timestamp fim_data_hora;
	private String ocorrencia;
	
	

}
