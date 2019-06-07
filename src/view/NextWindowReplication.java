package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class NextWindowReplication
		extends	JFrame
		implements NextWindowController {
	
	private JTextField txfDirecaoOrigem;
	private JTextField txfDirecaoDestino;
	private JTextField txfProcesso;
	private JTextField txfTabela;
	private JTextField txfTabelas;
	private JTextField txfErros;
	private JProgressBar pbbPrincipal;
	private JProgressBar pbbIndeterminada;
	private JButton btnReplicar;




	public static void main(String[] args) {
		NextWindowReplication np = new NextWindowReplication();
		np.setVisible(true);




	}


	public NextWindowReplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 327, 141);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);

		// Define o tamanho da janela.
		setSize(1270,768);

		// Define o titulo da janela.
		setTitle("Principal Window");

		// Seta o layout a ser utilizado (NULL significa que não irá utilizar nenhum).
		setLayout(null);

		// Define que não poderá ser alterado as dimensões da tela.
		setResizable(true);

		// Define o método de fechamento da janela.
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.black);

		setLocationRelativeTo(null);
		// Cria os componentes.

		btnReplicar = new JButton(new AbstractAction("REPLICAR") {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (btnReplicar.getText().equals("REPLICAR")) {

					NextReplicationExecute replication = new NextReplicationExecute(NextWindowReplication.this);
					replication.ReplicacaoIniciar();

				}

			}

		});
		btnReplicar.setBackground(SystemColor.menu);
		btnReplicar.setBounds(119, 11, 120, 31);
		getContentPane().add(btnReplicar);


	}


	@Override
	public void DirecaoExibir(String direcaoOrigem, String direcaoDestino) {
		txfDirecaoOrigem.setText(direcaoOrigem);
		txfDirecaoDestino.setText(direcaoDestino);
	}

	@Override
	public void ProcessoExibir(String processo) {
		txfProcesso.setText(processo);
	}

	@Override
	public void TabelaExibir(String tabela) {
		txfTabela.setText(tabela);
	}

	@Override
	public void TabelasExibir(int quantidade) {
		txfTabelas.setText(""+quantidade);
	}

	@Override
	public void ErrosExibir(int quantidade) {
		txfErros.setText(""+quantidade);
	}

	@Override
	public void BarraProgressoIndeterminadaIniciar(boolean start) {
		pbbIndeterminada.setIndeterminate(start);
	}

	@Override
	public void BarraProgressoValor(int valor) {
		pbbPrincipal.setValue(valor);
	}

}
