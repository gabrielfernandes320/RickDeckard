package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import database.AlunoDAO;
import database.ConectionsReplicationDAO;
import database.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


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


	public NextWindowReplication() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 327, 141);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);

		// Define o tamanho da janela.
		setSize(382,345);

		// Define o titulo da janela.
		setTitle("Replica\u00E7\u00E3o");

		// Seta o layout a ser utilizado (NULL significa que não irá utilizar nenhum).
		getContentPane().setLayout(null);

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
		btnReplicar.setBounds(10, 264, 120, 31);
		getContentPane().add(btnReplicar);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(140, 265, 216, 31);
		getContentPane().add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Conex\u00E3o:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(84, 85, 96, 17);
		getContentPane().add(lblNewLabel);
		
		Connection conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");
		
		JComboBox conectionCmb = new JComboBox();
		conectionCmb.setModel(new DefaultComboBoxModel(loadConectionsComboBox()));
		conectionCmb.setBounds(156, 85, 120, 20);
		getContentPane().add(conectionCmb);
		
		JLabel lblDireo = new JLabel("Dire\u00E7\u00E3o:");
		lblDireo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireo.setBounds(84, 113, 96, 17);
		getContentPane().add(lblDireo);
		
		JComboBox directionCmb = new JComboBox();
		directionCmb.setBounds(156, 113, 120, 20);
		getContentPane().add(directionCmb);
		
		JLabel lblProcesso = new JLabel("Processo:");
		lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProcesso.setBounds(84, 144, 96, 17);
		getContentPane().add(lblProcesso);
		
		JComboBox processCmb = new JComboBox();
		processCmb.setBounds(156, 144, 120, 20);
		getContentPane().add(processCmb);


	}

	String[] loadConectionsComboBox() throws SQLException {

		Connection conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");

		ConectionsReplicationDAO dao = new ConectionsReplicationDAO(conn);
		String[] Connections = dao.selectConectionNames();

		return Connections;

	}
	
	String[] loadDirectionsComboBox() throws SQLException {

		Connection conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");

		PORRA VOU FAZER ESSA MERDA dao = new ConectionsReplicationDAO(conn);
		String[] Connections = dao.selectConectionNames();

		return Connections;

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
