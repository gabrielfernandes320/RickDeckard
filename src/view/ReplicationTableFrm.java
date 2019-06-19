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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import database.ConnectionFactory;
import database.TableReplicationDAO;
import database.TableReplicationExecutionDAO;
import model.TableReplicationExecution;
import model.TbTableReplication;

import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



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
	private JTextField txf_proccess;
	private JTextField txf_order;
	private JTextField txf_source_table;
	private JTextField txf_operation;
	private JTextField txf_destiny_table;
	private JTextField txf_save;
	private JLabel lblColunaTipo;
	private JLabel lblColunaChave;
	private JTextField txf_key_column;
	private JComboBox comboBox;
	
	Connection conn = ConnectionFactory.getConnection("nextdb", "admin", "admin");


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
		getContentPane().add(btnBuscar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setEnabled(false);
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		btnRemover = new JButton("Remover");
		btnRemover.setPreferredSize(new Dimension(40, 25));
		btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(216, 11, 114, 31);
		getContentPane().add(btnRemover);

		JLabel lblProcesso = new JLabel("Processo:");
		lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProcesso.setBounds(49, 79, 63, 14);
		getContentPane().add(lblProcesso);
		
		JCheckBox chckbxIgnorarErro = new JCheckBox("Ignorar Erro");
		chckbxIgnorarErro.setEnabled(false);
		chckbxIgnorarErro.setBounds(122, 230, 97, 23);
		getContentPane().add(chckbxIgnorarErro);
		
		JCheckBox chckbxHabilitarEdio = new JCheckBox("Habilitar Edi\u00E7\u00E3o");
		chckbxHabilitarEdio.setEnabled(false);
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
		
		txf_proccess = new JTextField();
		txf_proccess.setText("Clique...");
		txf_proccess.setEditable(false);
		txf_proccess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ProccessSearchFrm proccess_search_frm = new ProccessSearchFrm(ReplicationTableFrm.this);
				proccess_search_frm.setVisible(true);
				
			}
		});
		txf_order = new JTextField();
		txf_proccess.setToolTipText("");
		txf_proccess.setBounds(122, 78, 150, 20);
		getContentPane().add(txf_proccess);
		txf_proccess.setColumns(10);
		
//		try {
//			txf_order = new JFormattedTextField(maskFormatter("#######"));
//			
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		txf_order.setColumns(10);
		txf_order.setBounds(122, 103, 150, 20);
		getContentPane().add(txf_order);
		
		txf_source_table = new JTextField();
		txf_source_table.setColumns(10);
		txf_source_table.setBounds(122, 128, 320, 20);
		getContentPane().add(txf_source_table);
		
		txf_operation = new JTextField();
		txf_operation.setEnabled(false);
		txf_operation.setColumns(10);
		txf_operation.setBounds(122, 153, 150, 20);
		getContentPane().add(txf_operation);
		
		txf_destiny_table = new JTextField();
		txf_destiny_table.setColumns(10);
		txf_destiny_table.setBounds(122, 178, 320, 20);
		getContentPane().add(txf_destiny_table);
		
		txf_save = new JTextField();
		txf_save.setEnabled(false);
		txf_save.setColumns(10);
		txf_save.setBounds(122, 203, 150, 20);
		getContentPane().add(txf_save);
		
		lblColunaTipo = new JLabel("Coluna Tipo:");
		lblColunaTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColunaTipo.setBounds(30, 315, 82, 14);
		getContentPane().add(lblColunaTipo);
		
		lblColunaChave = new JLabel("Coluna Chave:");
		lblColunaChave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColunaChave.setBounds(20, 290, 96, 14);
		getContentPane().add(lblColunaChave);
		
		txf_key_column = new JTextField();
		txf_key_column.setColumns(10);
		txf_key_column.setBounds(122, 289, 320, 20);
		getContentPane().add(txf_key_column);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--------------------------------- Selecione--------------------------", "int", "String"}));
		comboBox.setBounds(122, 313, 320, 22);
		getContentPane().add(comboBox);

		//	-------------Actions------------------------------------
		

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if( (txf_proccess.getText()) == "clique..." || (txf_order.getText()).isEmpty()||
					(txf_source_table.getText()).isEmpty() || (txf_destiny_table.getText()).isEmpty()||
					(comboBox.getSelectedItem()) == "--------------------------------- Selecione--------------------------") {
					JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos");
				}else {
					
					try {
						conn.setAutoCommit(false);
						System.out.println("Conectado com sucesso!");
						
						TableReplicationDAO tableReplicationDAO = new TableReplicationDAO(conn);
						TbTableReplication model = new TbTableReplication( "admin",txf_proccess.getText(),
								Integer.parseInt(txf_order.getText()), txf_source_table.getText(),
								txf_destiny_table.getText(),comboBox.getSelectedItem().toString(),txf_key_column.getText());
						
						tableReplicationDAO.Insert(model);
						
						txf_proccess.setText("clique...");
						txf_order.setText("");
						txf_source_table.setText("");
						txf_destiny_table.setText("");
						txf_key_column.setText("");
						comboBox.setSelectedIndex(0);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			
				
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableSearchFrm table_rep_search_frm = new TableSearchFrm(ReplicationTableFrm.this);
				table_rep_search_frm.setVisible(true);
				btnAdicionar.setEnabled(false);
				
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
					System.out.println("Conectado com sucesso!");
					
					TableReplicationDAO tableReplicationDAO = new TableReplicationDAO(conn);
					TbTableReplication model = new TbTableReplication();
					model.setTabela_origem(txf_source_table.getText());;
					tableReplicationDAO.Delete(model);
					
					txf_proccess.setText("clique...");
					txf_order.setText("");
					txf_source_table.setText("");
					txf_destiny_table.setText("");
					txf_key_column.setText("");
					comboBox.setSelectedIndex(0);
					btnAdicionar.setEnabled(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

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
	
	public MaskFormatter maskFormatter(String format) throws ParseException {
		try {
			return (new MaskFormatter(format));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public void Update(TbTableReplication t) {
		txf_proccess.setText(t.getProcesso());
	
	}
	
	public void update(TbTableReplication t) {
		txf_source_table.setText(t.getTabela_destino());
		txf_order.setText(t.getOrdem().toString());
		txf_source_table.setText(t.getTabela_origem());
		txf_destiny_table.setText(t.getTabela_destino());
		txf_key_column.setText(t.getColuna_chave());
		
		comboBox.setSelectedIndex(1);
		if(t.getColuna_tipo() != comboBox.getSelectedItem()) {
			comboBox.setSelectedIndex(2);
		}
	}
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}