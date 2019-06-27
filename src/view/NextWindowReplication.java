package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import database.TableReplicationDirectionDAO;
import database.TableReplicationProcessDAO;
import database.ConectionsReplicationDAO;
import database.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class NextWindowReplication<E> extends JInternalFrame implements ActionListener{

	private JLabel origemLbl;
	private JLabel destinoLbl;
	private JLabel pointerLbl;
	private JTextField txfProcesso;
	private JTextField txfTabela;
	private JTextField txfTabelas;
	private JTextField txfErros;
	private JComboBox<E> processCmb;
	private JComboBox<E> directionCmb;
	private JProgressBar pbbPrincipal = new JProgressBar();
	private JProgressBar pbbIndeterminada;
	private int progresso;
	private JButton btnReplicar;

	public static void main(String[] args) throws SQLException {
		NextWindowReplication np = new NextWindowReplication();
		np.setVisible(true);

	}

	public NextWindowReplication() throws SQLException {
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 327, 141);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);

		// Define o tamanho da janela.
		setSize(382, 345);

		// Define o titulo da janela.
		setTitle("Replica\u00E7\u00E3o");

		// Define que não poderá ser alterado as dimensões da tela.
		setResizable(true);

		// Define o método de fechamento da janela.
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);

		setLocation(1, 1);
		getContentPane().setLayout(null);
		// Cria os componentes.

		btnReplicar = new JButton(new AbstractAction("REPLICAR") {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (btnReplicar.getText().equals("REPLICAR")) {

					NextReplicationExecute replication = null;
					try {
						replication = new NextReplicationExecute(NextWindowReplication.this);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					replication.ReplicacaoIniciar();

				}

			}

		});
		btnReplicar.setBounds(10, 264, 120, 31);
		btnReplicar.setBackground(SystemColor.menu);
		getContentPane().add(btnReplicar);

		pbbPrincipal.setBounds(140, 265, 216, 31);
		pbbPrincipal.setStringPainted(true);
		getContentPane().add(pbbPrincipal);

		Connection conn = ConnectionFactory.getConnection("nextDB", "admin", "admin");

		JLabel lblProcesso = new JLabel("Processo:");
		lblProcesso.setBounds(10, 11, 96, 17);
		lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblProcesso);

		processCmb = new JComboBox();
		processCmb.setBounds(82, 11, 120, 20);
		processCmb.setModel(new DefaultComboBoxModel(loadProcessComboBox()));
		processCmb.addActionListener(this);
		getContentPane().add(processCmb);

		JLabel lblDireo = new JLabel("Dire\u00E7\u00E3o:");
		lblDireo.setBounds(10, 39, 96, 17);
		lblDireo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblDireo);

		directionCmb = new JComboBox();
		directionCmb.setBounds(82, 39, 120, 20);
		getContentPane().add(directionCmb);

		origemLbl = new JLabel("");
		origemLbl.setBounds(212, 42, 64, 14);
		getContentPane().add(origemLbl);

		destinoLbl = new JLabel("");
		destinoLbl.setBounds(292, 42, 64, 14);
		getContentPane().add(destinoLbl);

		pointerLbl = new JLabel("->");
		pointerLbl.setBounds(273, 41, 24, 17);
		getContentPane().add(pointerLbl);

	}

    public void actionPerformed(ActionEvent e)
    {
    	
        try {
        	directionCmb.setModel(new DefaultComboBoxModel(loadDirectionsComboBox()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    }
	
	
	String[] loadProcessComboBox() throws SQLException {

		Connection conn = ConnectionFactory.getConnection("nextDB", "admin", "admin");

		TableReplicationProcessDAO dao = new TableReplicationProcessDAO(conn);
		String[] Process = dao.selectProcessNames();
				
		return Process;

	}
	
	String[] loadDirectionsComboBox() throws SQLException {

		Connection conn = ConnectionFactory.getConnection("nextDB", "admin", "admin");

		TableReplicationDirectionDAO dao = new TableReplicationDirectionDAO(conn);
					
		String [] Directions = dao.selectDirectionNames(processCmb.getSelectedItem().toString());
						
		return Directions;
		
	}

	public void UpdateFields() throws SQLException {

		Connection conn = ConnectionFactory.getConnection("nextDB", "admin", "admin");

		TableReplicationDirectionDAO directionDAO = new TableReplicationDirectionDAO(conn);


	}

	public int getDirection() {
		
		Integer result = Integer.valueOf(directionCmb.getSelectedItem().toString());
		return result;
		
	}
	
	public String getProcess() {
		
		return processCmb.getSelectedItem().toString();
		
	}
	
	public int getProgress() {
		
		return progresso;
		
	}
	
	public void setProgress(int newvalue) {
		
		progresso = newvalue;
		pbbPrincipal.setValue(newvalue);
		
		if (newvalue >= 100) {
			
			pbbPrincipal.setValue(100);
			JOptionPane.showMessageDialog(getContentPane(), "Sucesso!", "Alerta:", JOptionPane.INFORMATION_MESSAGE);	
			
		}
		
		
	}


	
}
