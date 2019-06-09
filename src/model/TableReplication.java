package model;

import java.sql.Timestamp;

public class TableReplication {
	private int codigo_processo	;
	private Timestamp data_atual;			
	private String usuario;     
	private String processo;			
	private String descricao;		
	private Timestamp Data_Atual_De;		
	private char erro_ignorar;		
	private Boolean habilitado;
	
	public int getCodigo_processo() {
		return codigo_processo;
	}
	public void setCodigo_processo(int codigo_processo) {
		this.codigo_processo = codigo_processo;
	}
	public Timestamp getData_atual() {
		return data_atual;
	}
	public void setData_atual(Timestamp data_atual) {
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Timestamp getData_Atual_De() {
		return Data_Atual_De;
	}
	public void setData_Atual_De(Timestamp data_Atual_De) {
		Data_Atual_De = data_Atual_De;
	}
	public char getErro_ignorar() {
		return erro_ignorar;
	}
	public void setErro_ignorar(char erro_ignorar) {
		this.erro_ignorar = erro_ignorar;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
