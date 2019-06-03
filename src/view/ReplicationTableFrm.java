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



public class ReplicationTableFrm extends JInternalFrame {
	private String[] modalidades = null;
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnSalvar;
	private JButton btnRemover;
	private Boolean IsUpdate;
	private JLabel lblOrdem;
	private JLabel lblTabelaOrigem;
	private JLabel lblOperao;
	private JLabel lblTabelaDestino;
	private JLabel lblSalvar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblColunaTipo;
	private JLabel lblColunaChave;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public ReplicationTableFrm() {
		setBounds(100, 100, 470, 398);
		getContentPane().setLayout(null);
		setResizable(false);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 11, 96, 31);
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		btnBuscar.setBackground(new Color(240, 240, 240));
		btnBuscar.setIcon(new ImageIcon(ReplicationTableFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnBuscar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(ReplicationTableFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ReplicationTableFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(ReplicationTableFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setPreferredSize(new Dimension(40, 25));
		btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(216, 11, 114, 31);
		getContentPane().add(btnRemover);

		JLabel lblProcesso = new JLabel("Processo:");
		lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProcesso.setBounds(49, 79, 63, 14);
		getContentPane().add(lblProcesso);
		
		JCheckBox chckbxIgnorarErro = new JCheckBox("Ignorar Erro");
		chckbxIgnorarErro.setBounds(122, 230, 97, 23);
		getContentPane().add(chckbxIgnorarErro);
		
		JCheckBox chckbxHabilitarEdio = new JCheckBox("Habilitar Edi\u00E7\u00E3o");
		chckbxHabilitarEdio.setBounds(122, 260, 114, 23);
		getContentPane().add(chckbxHabilitarEdio);
		
		lblOrdem = new JLabel("Ordem:");
		lblOrdem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOrdem.setBounds(62, 104, 50, 14);
		getContentPane().add(lblOrdem);
		
		lblTabelaOrigem = new JLabel("Tabela Origem:");
		lblTabelaOrigem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTabelaOrigem.setBounds(10, 129, 102, 14);
		getContentPane().add(lblTabelaOrigem);
		
		lblOperao = new JLabel("Opera\u00E7\u00E3o:");
		lblOperao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOperao.setBounds(44, 154, 68, 14);
		getContentPane().add(lblOperao);
		
		lblTabelaDestino = new JLabel("Tabela Destino:");
		lblTabelaDestino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTabelaDestino.setBounds(9, 179, 103, 14);
		getContentPane().add(lblTabelaDestino);
		
		lblSalvar = new JLabel("Salvar:");
		lblSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSalvar.setBounds(68, 204, 44, 14);
		getContentPane().add(lblSalvar);
		
		textField = new JTextField();
		textField.setBounds(122, 78, 150, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(122, 103, 150, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(122, 128, 320, 20);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(122, 153, 150, 20);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(122, 178, 320, 20);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(122, 203, 150, 20);
		getContentPane().add(textField_5);
		
		lblColunaTipo = new JLabel("Coluna Tipo:");
		lblColunaTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColunaTipo.setBounds(30, 315, 82, 14);
		getContentPane().add(lblColunaTipo);
		
		lblColunaChave = new JLabel("Coluna Chave:");
		lblColunaChave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColunaChave.setBounds(20, 290, 96, 14);
		getContentPane().add(lblColunaChave);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(122, 289, 320, 20);
		getContentPane().add(textField_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 313, 320, 22);
		getContentPane().add(comboBox);

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


