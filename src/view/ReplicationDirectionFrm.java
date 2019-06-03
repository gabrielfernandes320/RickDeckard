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
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;



public class ReplicationDirectionFrm extends JInternalFrame {
	private String[] modalidades = null;
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnSalvar;
	private JButton btnRemover;
	private Boolean IsUpdate;
	private JLabel lblOrdem;
	private JLabel lblTabelaOrigem;
	private JLabel lblOperao;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox_1;
	private JPanel panel_1;
	private JTextField textField_1;
	private JLabel lblSenha;
	private JLabel lblUsuario_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblUsuario;
	private JPanel panel_2;
	private JTextField textField_6;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField_7;
	private JTextField textField_8;
	private JLabel label_2;
	private JPanel panel_3;
	private JTextField textField_9;
	private JLabel lblHora;
	private JLabel lblMs;
	private JTextField textField_10;
	private JTextField textField_11;
	private JLabel lblAno;
	private JLabel lblMinuto;
	private JLabel lblMinuto_1;
	private JLabel lblSegundo;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;

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
		btnBuscar.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnBuscar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(ReplicationDirectionFrm.class.getResource("/view/images/remover.png")));
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
				
				lblOperao = new JLabel("(\u00D1 Consegui ler)");
				lblOperao.setForeground(Color.RED);
				lblOperao.setBounds(11, 87, 102, 14);
				panel.add(lblOperao);
				lblOperao.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				textField = new JTextField();
				textField.setBounds(123, 11, 150, 20);
				panel.add(textField);
				textField.setColumns(10);
				
				textField_2 = new JTextField();
				textField_2.setBounds(123, 61, 150, 20);
				panel.add(textField_2);
				textField_2.setColumns(10);
				
				textField_3 = new JTextField();
				textField_3.setBounds(123, 86, 150, 20);
				panel.add(textField_3);
				textField_3.setColumns(10);
				
				comboBox_1 = new JComboBox();
				comboBox_1.setBounds(123, 35, 150, 22);
				panel.add(comboBox_1);
				
				panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Origem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(11, 146, 272, 109);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(86, 73, 170, 20);
				panel_1.add(textField_1);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setForeground(Color.BLACK);
				lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSenha.setBounds(31, 74, 45, 14);
				panel_1.add(lblSenha);
				
				lblUsuario_1 = new JLabel("Usuario:");
				lblUsuario_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblUsuario_1.setBounds(20, 49, 54, 14);
				panel_1.add(lblUsuario_1);
				
				textField_4 = new JTextField();
				textField_4.setColumns(10);
				textField_4.setBounds(86, 48, 170, 20);
				panel_1.add(textField_4);
				
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(86, 23, 170, 20);
				panel_1.add(textField_5);
				
				lblUsuario = new JLabel("Database:");
				lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblUsuario.setBounds(10, 24, 67, 14);
				panel_1.add(lblUsuario);
				
				panel_2 = new JPanel();
				panel_2.setLayout(null);
				panel_2.setBorder(new TitledBorder(null, "Origem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_2.setBounds(293, 146, 272, 109);
				panel.add(panel_2);
				
				textField_6 = new JTextField();
				textField_6.setColumns(10);
				textField_6.setBounds(86, 73, 170, 20);
				panel_2.add(textField_6);
				
				label = new JLabel("Senha:");
				label.setForeground(Color.BLACK);
				label.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label.setBounds(31, 74, 45, 14);
				panel_2.add(label);
				
				label_1 = new JLabel("Usuario:");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label_1.setBounds(22, 49, 54, 14);
				panel_2.add(label_1);
				
				textField_7 = new JTextField();
				textField_7.setColumns(10);
				textField_7.setBounds(86, 48, 170, 20);
				panel_2.add(textField_7);
				
				textField_8 = new JTextField();
				textField_8.setColumns(10);
				textField_8.setBounds(86, 23, 170, 20);
				panel_2.add(textField_8);
				
				label_2 = new JLabel("Database:");
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label_2.setBounds(10, 24, 67, 14);
				panel_2.add(label_2);
				
				panel_3 = new JPanel();
				panel_3.setLayout(null);
				panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Per\u00EDodo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_3.setBounds(11, 266, 554, 109);
				panel.add(panel_3);
				
				textField_9 = new JTextField();
				textField_9.setColumns(10);
				textField_9.setBounds(51, 73, 202, 20);
				panel_3.add(textField_9);
				
				lblHora = new JLabel("Dia:");
				lblHora.setForeground(Color.BLACK);
				lblHora.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblHora.setBounds(10, 74, 26, 14);
				panel_3.add(lblHora);
				
				textField_10 = new JTextField();
				textField_10.setColumns(10);
				textField_10.setBounds(50, 48, 203, 20);
				panel_3.add(textField_10);
				
				textField_11 = new JTextField();
				textField_11.setColumns(10);
				textField_11.setBounds(50, 23, 203, 20);
				panel_3.add(textField_11);
				
				lblAno = new JLabel("Ano:");
				lblAno.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAno.setBounds(10, 24, 30, 14);
				panel_3.add(lblAno);
				
				lblMinuto = new JLabel("Hora:");
				lblMinuto.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblMinuto.setBounds(286, 24, 36, 14);
				panel_3.add(lblMinuto);
				
				lblMinuto_1 = new JLabel("Minuto:");
				lblMinuto_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblMinuto_1.setBounds(273, 49, 49, 14);
				panel_3.add(lblMinuto_1);
				
				lblSegundo = new JLabel("Segundo:");
				lblSegundo.setForeground(Color.BLACK);
				lblSegundo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSegundo.setBounds(263, 74, 61, 14);
				panel_3.add(lblSegundo);
				
				lblMs = new JLabel("M\u00EAs:");
				lblMs.setBounds(10, 49, 32, 14);
				panel_3.add(lblMs);
				lblMs.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				textField_12 = new JTextField();
				textField_12.setColumns(10);
				textField_12.setBounds(332, 23, 202, 20);
				panel_3.add(textField_12);
				
				textField_13 = new JTextField();
				textField_13.setColumns(10);
				textField_13.setBounds(332, 48, 202, 20);
				panel_3.add(textField_13);
				
				textField_14 = new JTextField();
				textField_14.setColumns(10);
				textField_14.setBounds(332, 73, 202, 20);
				panel_3.add(textField_14);

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


