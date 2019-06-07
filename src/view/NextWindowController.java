package view;
public interface NextWindowController {

	public void DirecaoExibir
	(
		final String direcaoOrigem,
		final String direcaoDestino
	);	
	
	public void ProcessoExibir
	(
		final String processo	
	);
	
	public void TabelaExibir
	(
		final String tabela
	);
	
	public void TabelasExibir
	(
		final int quantidade
	);
	
	public void ErrosExibir
	(
		final int quantidade
	);
	
	public void BarraProgressoIndeterminadaIniciar
	(
		final boolean start
	);
	
	public void BarraProgressoValor
	(
		final int valor
	);
	
}
