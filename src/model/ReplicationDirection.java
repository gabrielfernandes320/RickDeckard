package model;

import java.sql.Date;

public class ReplicationDirection {
	
	int codigo_direcao;
	int duracao;
	int retencao;
	Date data_atual;
	String usuario;
	String processo;
	String database_origem;
	String usuario_origem;
	String senha_origem;
	String database_destino;
	String usuario_destino;
	String senha_destino;
	char auto_manual;
	char habilitado;

	//provavelmente não será utilizado
	int ano;
	int mes;
	int dia;
	int hora;
	int minuto;
	int segundo;
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public int getSegundo() {
		return segundo;
	}
	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}
	public int getRetencao() {
		return retencao;
	}
	public void setRetencao(int retencao) {
		this.retencao = retencao;
	}
	public char getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(char habilitado) {
		this.habilitado = habilitado;
	}
	public int getCodigo_direcao(int i) {
		return codigo_direcao;
	}
	public void setCodigo_direcao(int codigo_direcao) {
		this.codigo_direcao = codigo_direcao;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public Date getData_atual() {
		return data_atual;
	}
	public void setData_atual(Date data_atual) {
		this.data_atual = data_atual;
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
	public String getUsuario_origem() {
		return usuario_origem;
	}
	public void setUsuario_origem(String usuario_origem) {
		this.usuario_origem = usuario_origem;
	}
	public String getSenha_origem() {
		return senha_origem;
	}
	public void setSenha_origem(String senha_origem) {
		this.senha_origem = senha_origem;
	}
	public String getDatabase_destino() {
		return database_destino;
	}
	public void setDatabase_destino(String database_destino) {
		this.database_destino = database_destino;
	}
	public String getUsuario_destino() {
		return usuario_destino;
	}
	public void setUsuario_destino(String usuario_destino) {
		this.usuario_destino = usuario_destino;
	}
	public String getSenha_destino() {
		return senha_destino;
	}
	public void setSenha_destino(String senha_destino) {
		this.senha_destino = senha_destino;
	}
	public char getAuto_manual() {
		return auto_manual;
	}
	public void setAuto_manual(char auto_manual) {
		this.auto_manual = auto_manual;
	}
	
	
	
	
}
