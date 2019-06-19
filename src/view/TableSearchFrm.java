package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import database.TableReplicationProcessDAO;
import model.TbTableReplication;
import tableModel.ProccessSearchTable;
import tableModel.TableReplicationTableModel;

public class TableSearchFrm extends JDialog {

	private JTable table;
	private TableReplicationTableModel model;
	private String idSelecionado;
	Connection conn = ConnectionFactory.getConnection("nextdb", "admin", "admin");
	private static ReplicationTableFrm window;
	private JTextField txfSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TableSearchFrm dialog = new TableSearchFrm(window);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TableSearchFrm(final ReplicationTableFrm window) {
		setTitle("Pesquisa de tabelas por processos");
		setBounds(100, 100, 471, 330);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 248, 434, 33);
			getContentPane().add(buttonPane);
			{
				buttonPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Duplo clique na linha para selecionar o item desejado");
				lblNewLabel.setBounds(0, 9, 434, 14);
				buttonPane.add(lblNewLabel);
			}
		}
		
		txfSearch = new JTextField();
		txfSearch.setBounds(89, 11, 247, 20);
		getContentPane().add(txfSearch);
		txfSearch.setColumns(10);
		
		JLabel lblProcesso= new JLabel("Processo:");
		lblProcesso.setBounds(10, 13, 75, 17);
		getContentPane().add(lblProcesso);
		
		JButton btnAtualizar = new JButton("Pesquisar");
		btnAtualizar.setBackground(SystemColor.menu);
		btnAtualizar.setBounds(340, 10, 104, 23);
		getContentPane().add(btnAtualizar);
		txfSearch.setText("");
		JPanel painelFundo;

		painelFundo = new JPanel();
		model = new TableReplicationTableModel();
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
			            t = (TbTableReplication) model.getValueAt(tabela.getSelectedRow(), 0);
			            window.update(t);  
			           	dispose();
		            }
		         }
		        } );
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zerarTodos();
				try {
					
					model.addListaDeProcessos(new TableReplicationDAO(conn).SelectAll(txfSearch.getText().toString()));
					
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
