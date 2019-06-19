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

import database.ConnectionFactory;
import database.MatriculaDAO;
import database.TableReplicationDirectionDAO;
import model.Matricula;
import model.ReplicationDirection;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;



public class ReplicationDirectionFrm extends JInternalFrame {
	private String[] modalidades = null;
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnSalvar;
	private JButton btnRemover;
	private Boolean IsUpdate;
	private JLabel lblOrdem;
	private JLabel lblTabelaOrigem;
	private JLabel lblRetencao;
	private JTextField txfProcesso;
	private JTextField txfDuracao;
	private JTextField txfRetencao;
	private JComboBox comboBox_1;
	private JPanel panel_1;
	private JTextField txfSenhaOrigem;
	private JLabel lblSenha;
	private JLabel lblUsuario_1;
	private JTextField txfUsuarioOrigem;
	private JTextField txfDbOrigem;
	private JLabel lblUsuario;
	private JPanel panel_2;
	private JTextField txfSenhaDestino;
	private JLabel label;
	private JLabel label_1;
	private JTextField txfUsuarioDestino;
	private JTextField txfDbDestino;
	private JLabel label_2;
	private JPanel panelPeriodo;
	private JTextField txfDia;
	private JLabel lblHora;
	private JLabel lblMs;
	private JTextField txfMes;
	private JTextField txfAno;
	private JLabel lblAno;
	private JLabel lblMinuto;
	private JLabel lblMinuto_1;
	private JLabel lblSegundo;
	private JTextField txfHora;
	private JTextField txfMinuto;
	private JTextField txfSegundo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public ReplicationDirectionFrm() {
		setBounds(100, 100, 609, 488);
		getContentPane().setLayout(null);
		setResizable(false);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 11, 96, 31);
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		btnBuscar.setBackground(new Color(240, 240, 240));
		//btnBuscar.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnBuscar);

		btnAdicionar = new JButton("Adicionar");
		//btnAdicionar.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		btnSalvar = new JButton("Salvar");
		//btnSalvar.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		btnRemover = new JButton("Remover");
		//btnRemover.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setPreferredSize(new Dimension(40, 25));
		btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(216, 11, 114, 31);
		getContentPane().add(btnRemover);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dire\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 53, 576, 381);
		getContentPane().add(panel);
		panel.setLayout(null);
		
				JLabel lblProcesso = new JLabel("Processo:");
				lblProcesso.setBounds(50, 12, 63, 14);
				panel.add(lblProcesso);
				lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JCheckBox chckbxHabilitarEdio = new JCheckBox("Habilitar Edi\u00E7\u00E3o");
				chckbxHabilitarEdio.setBounds(123, 116, 114, 23);
				panel.add(chckbxHabilitarEdio);
				
				lblOrdem = new JLabel("Aut/Man:");
				lblOrdem.setBounds(50, 37, 63, 14);
				panel.add(lblOrdem);
				lblOrdem.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				lblTabelaOrigem = new JLabel("Dura\u00E7\u00E3o:");
				lblTabelaOrigem.setBounds(50, 62, 63, 14);
				panel.add(lblTabelaOrigem);
				lblTabelaOrigem.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				lblRetencao = new JLabel("Opera\u00E7\u00E3o:");
				lblRetencao.setHorizontalAlignment(SwingConstants.RIGHT);
				lblRetencao.setForeground(Color.BLACK);
				lblRetencao.setBounds(11, 87, 102, 14);
				panel.add(lblRetencao);
				lblRetencao.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				txfProcesso = new JTextField();
				txfProcesso.setBounds(123, 11, 150, 20);
				panel.add(txfProcesso);
				txfProcesso.setColumns(10);
				
				txfDuracao = new JTextField();
				txfDuracao.setBounds(123, 61, 150, 20);
				panel.add(txfDuracao);
				txfDuracao.setColumns(10);
				
				txfRetencao = new JTextField();
				txfRetencao.setBounds(123, 86, 150, 20);
				panel.add(txfRetencao);
				txfRetencao.setColumns(10);
				
				comboBox_1 = new JComboBox();
				comboBox_1.setBounds(123, 35, 150, 22);
				panel.add(comboBox_1);
				comboBox_1.addItem("automatico");
				comboBox_1.addItem("manual");
				
				panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Origem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(11, 146, 272, 109);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				txfSenhaOrigem = new JTextField();
				txfSenhaOrigem.setColumns(10);
				txfSenhaOrigem.setBounds(86, 73, 170, 20);
				panel_1.add(txfSenhaOrigem);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setForeground(Color.BLACK);
				lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSenha.setBounds(31, 74, 45, 14);
				panel_1.add(lblSenha);
				
				lblUsuario_1 = new JLabel("Usuario:");
				lblUsuario_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblUsuario_1.setBounds(20, 49, 54, 14);
				panel_1.add(lblUsuario_1);
				
				txfUsuarioOrigem = new JTextField();
				txfUsuarioOrigem.setColumns(10);
				txfUsuarioOrigem.setBounds(86, 48, 170, 20);
				panel_1.add(txfUsuarioOrigem);
				
				txfDbOrigem = new JTextField();
				txfDbOrigem.setColumns(10);
				txfDbOrigem.setBounds(86, 23, 170, 20);
				panel_1.add(txfDbOrigem);
				
				lblUsuario = new JLabel("Database:");
				lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblUsuario.setBounds(10, 24, 67, 14);
				panel_1.add(lblUsuario);
				
				panel_2 = new JPanel();
				panel_2.setLayout(null);
				panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Destino", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_2.setBounds(293, 146, 272, 109);
				panel.add(panel_2);
				
				txfSenhaDestino = new JTextField();
				txfSenhaDestino.setColumns(10);
				txfSenhaDestino.setBounds(86, 73, 170, 20);
				panel_2.add(txfSenhaDestino);
				
				label = new JLabel("Senha:");
				label.setForeground(Color.BLACK);
				label.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label.setBounds(31, 74, 45, 14);
				panel_2.add(label);
				
				label_1 = new JLabel("Usuario:");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label_1.setBounds(22, 49, 54, 14);
				panel_2.add(label_1);
				
				txfUsuarioDestino = new JTextField();
				txfUsuarioDestino.setColumns(10);
				txfUsuarioDestino.setBounds(86, 48, 170, 20);
				panel_2.add(txfUsuarioDestino);
				
				txfDbDestino = new JTextField();
				txfDbDestino.setColumns(10);
				txfDbDestino.setBounds(86, 23, 170, 20);
				panel_2.add(txfDbDestino);
				
				label_2 = new JLabel("Database:");
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label_2.setBounds(10, 24, 67, 14);
				panel_2.add(label_2);
				
				panelPeriodo = new JPanel();
				panelPeriodo.setLayout(null);
				panelPeriodo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Per\u00EDodo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelPeriodo.setBounds(11, 266, 554, 109);
				panel.add(panelPeriodo);
				
				txfDia = new JTextField();
				txfDia.setColumns(10);
				txfDia.setBounds(51, 73, 202, 20);
				panelPeriodo.add(txfDia);
				
				lblHora = new JLabel("Dia:");
				lblHora.setForeground(Color.BLACK);
				lblHora.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblHora.setBounds(10, 74, 26, 14);
				panelPeriodo.add(lblHora);
				
				txfMes = new JTextField();
				txfMes.setColumns(10);
				txfMes.setBounds(50, 48, 203, 20);
				panelPeriodo.add(txfMes);
				
				txfAno = new JTextField();
				txfAno.setColumns(10);
				txfAno.setBounds(50, 23, 203, 20);
				panelPeriodo.add(txfAno);
				
				lblAno = new JLabel("Ano:");
				lblAno.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAno.setBounds(10, 24, 30, 14);
				panelPeriodo.add(lblAno);
				
				lblMinuto = new JLabel("Hora:");
				lblMinuto.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblMinuto.setBounds(286, 24, 36, 14);
				panelPeriodo.add(lblMinuto);
				
				lblMinuto_1 = new JLabel("Minuto:");
				lblMinuto_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblMinuto_1.setBounds(273, 49, 49, 14);
				panelPeriodo.add(lblMinuto_1);
				
				lblSegundo = new JLabel("Segundo:");
				lblSegundo.setForeground(Color.BLACK);
				lblSegundo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSegundo.setBounds(263, 74, 61, 14);
				panelPeriodo.add(lblSegundo);
				
				lblMs = new JLabel("M\u00EAs:");
				lblMs.setBounds(10, 49, 32, 14);
				panelPeriodo.add(lblMs);
				lblMs.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				txfHora = new JTextField();
				txfHora.setColumns(10);
				txfHora.setBounds(332, 23, 202, 20);
				panelPeriodo.add(txfHora);
				
				txfMinuto = new JTextField();
				txfMinuto.setColumns(10);
				txfMinuto.setBounds(332, 48, 202, 20);
				panelPeriodo.add(txfMinuto);
				
				txfSegundo = new JTextField();
				txfSegundo.setColumns(10);
				txfSegundo.setBounds(332, 73, 202, 20);
				panelPeriodo.add(txfSegundo);

		// tem que colocar as modalidades dentro do JComboBox
//		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
//		PlanosDAO dao = new PlanosDAO(conn);
//		String modalidade[];
//		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

//		// PlanosDAO pla = new PlanosDAO(conn);
//
//		ModalidadesDAO mod;
//
//		try {
//			mod = new ModalidadesDAO(conn);
//			modalidades = mod.SelectAllModalidade();
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					TableReplicationDirectionDAO tableReplicationDirectionDAO = new TableReplicationDirectionDAO(conn);
					ReplicationDirection model = new ReplicationDirection();
					
					if (comboBox_1.getSelectedIndex() == 0) {
						model.setAuto_manual('a');
					} else {
						model.setAuto_manual('m');
					}
					
					if (chckbxHabilitarEdio.isSelected()) {
						model.setHabilitado('s');
					} else {
						model.setHabilitado('n');
					}
					
					model.setProcesso(txfProcesso.getText());
					model.setDuracao(Integer.parseInt(txfDuracao.getText()));
					model.setRetencao(Integer.parseInt(txfRetencao.getText()));
					model.setDatabase_origem(txfDbOrigem.getText());
					model.setUsuario_origem(txfUsuarioOrigem.getText());
					model.setSenha_origem(txfSenhaOrigem.getText());
					model.setDatabase_destino(txfDbDestino.getText());
					model.setUsuario_destino(txfUsuarioDestino.getText());
					model.setSenha_destino(txfSenhaDestino.getText());
					model.setAno(Integer.parseInt(txfAno.getText()));
					model.setMes(Integer.parseInt(txfMes.getText()));
					model.setDia(Integer.parseInt(txfDia.getText()));
					model.setHora(Integer.parseInt(txfHora.getText()));
					model.setMinuto(Integer.parseInt(txfMinuto.getText()));
					model.setSegundo(Integer.parseInt(txfSegundo.getText()));
					
					conn.setAutoCommit(false);
					TableReplicationDirectionDAO dao = new TableReplicationDirectionDAO(conn);
					
					dao.InsertDirecao(model);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Preencha todos os campos!");
				}
				
				
				
				
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

//	public void Update(final Plano p) {
//
//		txfPlano.setText(p.getPlano());
//		txfValor.setText("" + p.getValor());
//		cbxModalidade.setSelectedItem(p.getModalidade());
//		abrirBotoes();
//		updateCampos();
//		IsUpdate = true;
//
////	while()
////	cbxModalidade.setSelectedIndex();
////		
//	}

	public void abrirCampos() {
		
	}

	public void fecharCampos() {
	}

	public void abrirBotoes() {
		
	}

	public void fecharBotoes() {
		
	}

	public void esvaziarCampos() {
	
	}

	public void updateCampos() {
		
	}
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}


