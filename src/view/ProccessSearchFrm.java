package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.ConnectionFactory;
import database.TableReplicationDAO;
import database.TableReplicationExecutionDAO;
import database.TableReplicationProcessDAO;
import model.Graduacoes;
import model.Modalidades;
import model.Plano;
import model.TbTableReplication;
import tableModel.ProccessSearchTable;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class ProccessSearchFrm extends JDialog {
	private JTable table;
	private ProccessSearchTable model;
	private String idSelecionado;
	Connection conn = ConnectionFactory.getConnection("nextdb", "admin", "admin");
	private static ReplicationTableFrm window;
	private JTextField txfSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProccessSearchFrm dialog = new ProccessSearchFrm(window);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProccessSearchFrm(final ReplicationTableFrm window) {
		setBounds(100, 100, 471, 330);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 248, 434, 33);
			getContentPane().add(buttonPane);
			{
				buttonPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Duplo clique na linha para selecionar a modalidade desejada");
				lblNewLabel.setBounds(0, 9, 434, 14);
				buttonPane.add(lblNewLabel);
			}
		}
		
		txfSearch = new JTextField();
		txfSearch.setBounds(89, 11, 247, 20);
		getContentPane().add(txfSearch);
		txfSearch.setColumns(10);
		
		JLabel lblModalidade = new JLabel("Processo:");
		lblModalidade.setBounds(10, 13, 75, 17);
		getContentPane().add(lblModalidade);
		
		JButton btnAtualizar = new JButton("Pesquisar");
		btnAtualizar.setBackground(SystemColor.menu);
				btnAtualizar.setBounds(340, 10, 104, 23);
		getContentPane().add(btnAtualizar);
		txfSearch.setText("");
		JPanel painelFundo;

		painelFundo = new JPanel();
		model = new ProccessSearchTable();
		painelFundo.setLayout(null);
		painelFundo.setBounds(10, 42, 434, 195);
		getContentPane().add(painelFundo);
		
				JTable tabela = new JTable(model);
				tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JScrollPane barraRolagem = new JScrollPane(tabela);
				barraRolagem.setBounds(0, 0, 434, 195);
				painelFundo.add(barraRolagem);

		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				if (tabela.getSelectedRow() != -1) {
					idSelecionado = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
				}
			}
		});
		
		tabela.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
		            System.out.println(" double click" );
		             TbTableReplication t = new TbTableReplication();
//		            List<Graduacoes> graduationList = new ArrayList<>();
		            t.setProcesso(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
//		             p.setModalidade(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
//		            try {
//						GraduacoesDAO graduationDAO = new GraduacoesDAO(conn);
//						graduationList = graduationDAO.SelectAll(p.getModalidade().toString());
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//		            
		            window.Update(t);
		           	dispose();
//		           	
		            }
		         }
		        } );
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zerarTodos();
				try {
					
					model.addListaDeProcessos(new TableReplicationProcessDAO(conn).SelectAll(txfSearch.getText().toString()));
				
				} catch (Exception e1) {
					System.err.printf("Erro: %s.\n", e1.getMessage());
				}

				
				
				
			}
		});

	}
	public void zerarTodos() {
		model.limpar();
	}

}
