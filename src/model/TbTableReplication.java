package model;

public class TbTableReplication {
	
	private int codigo_replicacao;
	private String usuario;
	private String processo;
	private Integer ordem;
	private String tabela_origem;
	private String tabela_destino;
	private String coluna_tipo;
	private String coluna_chave;
	
	
	
	public TbTableReplication() {
		super();
	}
	public TbTableReplication( String usuario, String processo, Integer ordem, String tabela_origem,
			String tabela_destino, String coluna_tipo, String coluna_chave) {
		super();
		this.usuario = usuario;
		this.processo = processo;
		this.ordem = ordem;
		this.tabela_origem = tabela_origem;
		this.tabela_destino = tabela_destino;
		this.coluna_tipo = coluna_tipo;
		this.coluna_chave = coluna_chave;
	}
	public int getCodigo_replicacao() {
		return codigo_replicacao;
	}
	public void setCodigo_replicacao(int codigo_replicacao) {
		this.codigo_replicacao = codigo_replicacao;
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
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public String getTabela_origem() {
		return tabela_origem;
	}
	public void setTabela_origem(String tabela_origem) {
		this.tabela_origem = tabela_origem;
	}
	public String getTabela_destino() {
		return tabela_destino;
	}
	public void setTabela_destino(String tabela_destino) {
		this.tabela_destino = tabela_destino;
	}
	public String getColuna_tipo() {
		return coluna_tipo;
	}
	public void setColuna_tipo(String coluna_tipo) {
		this.coluna_tipo = coluna_tipo;
	}
	public String getColuna_chave() {
		return coluna_chave;
	}
	public void setColuna_chave(String coluna_chave) {
		this.coluna_chave = coluna_chave;
	}
	
}
