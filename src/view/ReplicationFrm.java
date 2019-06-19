package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.ConectionsReplicationDAO;
import database.ConnectionFactory;
import model.ConectionReplication;



public class ReplicationFrm extends JInternalFrame {
	private String[] modalidades = null;
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnSalvar;
	private JButton btnRemover;
	private Boolean IsUpdate;
	private JTextField tbDescription;
	private JTextField tbIP;
	private JTextField tbPort;
	private JTextField tbDBname;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public ReplicationFrm() {
		setBounds(100, 100, 470, 309);
		getContentPane().setLayout(null);
		setResizable(false);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 11, 96, 31);
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		//btnBuscar.setBackground(new Color(240, 240, 240));
		//btnBuscar.setIcon(new ImageIcon(ReplicationFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnBuscar);

		btnAdicionar = new JButton("Adicionar");
		//btnAdicionar.setIcon(new ImageIcon(ReplicationFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		//btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		btnSalvar = new JButton("Salvar");
		//btnSalvar.setIcon(new ImageIcon(ReplicationFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		//btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		btnRemover = new JButton("Remover");
		//btnRemover.setIcon(new ImageIcon(ReplicationFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setPreferredSize(new Dimension(40, 25));
		//btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(216, 11, 114, 31);
		getContentPane().add(btnRemover);

		JLabel lblDescricao = new JLabel("Descricao:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescricao.setBounds(60, 79, 68, 14);
		getContentPane().add(lblDescricao);

		JLabel lblEnderecoIP = new JLabel("Endereco (IP):");
		lblEnderecoIP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnderecoIP.setBounds(32, 104, 96, 14);
		getContentPane().add(lblEnderecoIP);

		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPorta.setBounds(89, 129, 39, 14);
		getContentPane().add(lblPorta);
		
		JLabel lblNomeDoBanco = new JLabel("Nome do Banco:");
		lblNomeDoBanco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomeDoBanco.setBounds(18, 160, 110, 14);
		getContentPane().add(lblNomeDoBanco);
		
		JLabel lblModeloDoBanco = new JLabel("Modelo do Banco:");
		lblModeloDoBanco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModeloDoBanco.setBounds(10, 192, 118, 14);
		getContentPane().add(lblModeloDoBanco);
		
		tbDescription = new JTextField();
		tbDescription.setBounds(138, 78, 304, 20);
		getContentPane().add(tbDescription);
		tbDescription.setColumns(10);
		
		tbIP = new JTextField();
		tbIP.setBounds(138, 103, 304, 20);
		getContentPane().add(tbIP);
		tbIP.setColumns(10);
		
		tbPort = new JTextField();
		tbPort.setBounds(138, 128, 68, 20);
		getContentPane().add(tbPort);
		tbPort.setColumns(10);
		
		tbDBname = new JTextField();
		tbDBname.setBounds(138, 159, 304, 20);
		getContentPane().add(tbDBname);
		tbDBname.setColumns(10);
		
		JComboBox cbDBmodel = new JComboBox();
		cbDBmodel.setModel(new DefaultComboBoxModel(new String[] {"MySQL", "PostGreSQL"}));
		cbDBmodel.setBounds(138, 190, 304, 22);
		getContentPane().add(cbDBmodel);
		
		JButton btnTestarConexo = new JButton("Testar Conexao");
		btnTestarConexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = ConnectionFactory.getConnection(tbIP.getText(), tbPort.getText(), tbDBname.getText(), "admin", "admin");
					JOptionPane.showMessageDialog(null,"Conectado com sucesso!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Erro na conexao!");
				}
				
			}
		});
		btnTestarConexo.setBounds(328, 234, 114, 23);
		getContentPane().add(btnTestarConexo);
//		
	
		btnSalvar.setEnabled(false);
		
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectionReplication conrep = new ConectionReplication();
				conrep.setConnectionAddress(tbIP.getText());
				conrep.setConnectionName(tbDescription.getText());
				conrep.setConnectionPort(tbPort.getText());
				conrep.setDatabaseSID(tbDBname.getText());
				conrep.setDatabaseType((String) cbDBmodel.getSelectedItem());
				conrep.setUser("gabriel");
				conrep.setDatabaseURL(getURL(tbIP.getText(), tbPort.getText(), tbDBname.getText()));
				
				Connection conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");
				ConectionsReplicationDAO crd = null;
				try {
					crd = new ConectionsReplicationDAO(conn);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				try {
					crd.Insert(conrep);
					JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Erro ao salvar!");
					e1.printStackTrace();
				}
				esvaziarCampos();
				btnRemover.setEnabled(true);
				btnAdicionar.setEnabled(true);
				
				
				
			
				
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				btnSalvar.setEnabled(true);
				btnRemover.setEnabled(false);
				btnAdicionar.setEnabled(false);
				

			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbDescription.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Campo vazio!");
				}
				else {
				ConectionReplication conrep = new ConectionReplication();
				conrep.setConnectionName(tbDescription.getText());
				
				Connection conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");
				ConectionsReplicationDAO crd = null;
				try {
					crd = new ConectionsReplicationDAO(conn);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				try {
					conrep = (ConectionReplication) crd.Select(conrep);
					tbDBname.setText(conrep.getDatabaseSID());
					tbIP.setText(conrep.getConnectionAddress());
					tbPort.setText(conrep.getConnectionPort());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				btnRemover.setEnabled(true);
				btnAdicionar.setEnabled(true);
				
				

			}
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConectionReplication conrep = new ConectionReplication();
				conrep.setConnectionName(tbDescription.getText());
				
				Connection conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");
				ConectionsReplicationDAO crd = null;
				try {
					crd = new ConectionsReplicationDAO(conn);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				try {
					crd.Delete(conrep);
					JOptionPane.showMessageDialog(null,"Apagado com sucesso!");
					esvaziarCampos();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Erro ao apagar!");
					e1.printStackTrace();
				}
				esvaziarCampos();
				
			}
		});
	}

	public String getURL(String ip, String port, String dbName) {
		String URL =  "jdbc:postgresql://"
				+	ip
				+	":"
				+	port
				+	"/"
				+	dbName;
		
		
		return URL;
	}
	
	
	public void abrirCampos() {
		tbDBname.setEnabled(true);
		tbDescription.setEnabled(true);
		tbIP.setEnabled(true);
		tbPort.setEnabled(true);
	}

	public void fecharCampos() {
		tbDBname.setEnabled(false);
		//tbDescription.setEnabled(false);
		tbIP.setEnabled(false);
		tbPort.setEnabled(false);
	}

	public void abrirBotoes() {
		
	}

	public void fecharBotoes() {
		
	}

	public void esvaziarCampos() {
		tbDBname.setText("");
		tbDescription.setText("");
		tbIP.setText("");
		tbPort.setText("");
	}

	public void updateCampos() {
		
	}
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}


