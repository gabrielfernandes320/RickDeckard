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
import database.TableReplicationProcessDAO;

import javax.swing.JCheckBox;


public class ReplicationProcessFrm extends JInternalFrame {
	
	private String[] modalidades = null;
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnSalvar;
	private JButton btnRemover;
	private Boolean IsUpdate;
	private JTextField ProcessField;
	private JTextField DescriptionField;
	private JTextField DateField;

	public ReplicationProcessFrm() {
		setBounds(100, 100, 470, 263);
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

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescricao.setBounds(10, 104, 68, 14);
		getContentPane().add(lblDescricao);

		JLabel lblProcesso = new JLabel("Processo:");
		lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProcesso.setBounds(15, 79, 63, 14);
		getContentPane().add(lblProcesso);

		JLabel lblData = new JLabel("Data de:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setBounds(20, 129, 57, 19);
		getContentPane().add(lblData);
		
		ProcessField = new JTextField();
		ProcessField.setBounds(88, 78, 354, 20);
		getContentPane().add(ProcessField);
		ProcessField.setColumns(10);
		
		DescriptionField = new JTextField();
		DescriptionField.setBounds(88, 103, 354, 20);
		getContentPane().add(DescriptionField);
		DescriptionField.setColumns(10);
		
		DateField = new JTextField();
		DateField.setBounds(87, 130, 152, 20);
		getContentPane().add(DateField);
		DateField.setColumns(10);
		
		JCheckBox chckbxIgnorarErro = new JCheckBox("Ignorar Erro");
		chckbxIgnorarErro.setBounds(138, 155, 97, 23);
		getContentPane().add(chckbxIgnorarErro);
		
		JCheckBox chckbxHabilitarEdio = new JCheckBox("Habilitar Edi\u00E7\u00E3o");
		chckbxHabilitarEdio.setBounds(138, 185, 114, 23);
		getContentPane().add(chckbxHabilitarEdio);
	
		Connection conn = ConnectionFactory.getConnection("nextDB", "admin", "admin");

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					conn.setAutoCommit(false);
					
					TableReplicationProcessDAO dao = new TableReplicationProcessDAO(conn);
					dao.Insert("master", ProcessField.getText(), DescriptionField.getText());
					
					JOptionPane.showMessageDialog(getContentPane(), "Salvo!", "Alerta:",
							JOptionPane.INFORMATION_MESSAGE);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}


