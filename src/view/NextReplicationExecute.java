package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.AlunoDAO;
import database.ConectionsReplicationDAO;
import database.ConnectionFactory;
import database.GraduacoesDAO;
import database.ModalidadesDAO;
import database.PlanosDAO;
import database.TableReplicationDAO;
import database.TableReplicationDirectionDAO;
import database.TableReplicationProcessDAO;
import database.UsuarioDAO;
import model.Aluno;
import model.ConectionReplication;
import model.Graduacoes;
import model.Modalidades;
import model.Plano;
import model.Usuario;

public class NextReplicationExecute {

	private NextWindowReplication io_window;
	private ConectionReplication origin_connection;
	private ConectionReplication destiny_connection;
	private Connection conn;
	private Connection conn_origin;
	private Connection conn_destiny;

	private int alunos;
	private int planos;
	private int graduacoes;
	private int modalidades;
	private int usuarios;
	
	private int progress;

	public NextReplicationExecute(NextWindowReplication window) throws SQLException {

		// Guardo a referencia.
		io_window = window;
		io_window.getProcess();

		// Instancia os DAO de controle.
		conn = ConnectionFactory.getConnection("masterReplicator", "admin", "admin");
		TableReplicationDirectionDAO DirectionDAO = new TableReplicationDirectionDAO(conn);
		ConectionsReplicationDAO ConnectionsDAO = new ConectionsReplicationDAO(conn);

		// Guardo a conexão.
		origin_connection.setDatabaseSID(DirectionDAO.selectOrigem(io_window.getDirection()));
		origin_connection = (ConectionReplication) ConnectionsDAO.Select(origin_connection);

		destiny_connection.setDatabaseSID(DirectionDAO.selectDestino(io_window.getDirection()));
		destiny_connection = (ConectionReplication) ConnectionsDAO.Select(destiny_connection);

		conn_origin = ConnectionFactory.getConnection(origin_connection.getConnectionAddress(),
				origin_connection.getConnectionPort(), origin_connection.getDatabaseSID(), "admin", "admin");
		conn_destiny = ConnectionFactory.getConnection(destiny_connection.getConnectionAddress(),
				destiny_connection.getConnectionPort(), destiny_connection.getDatabaseSID(), "admin", "admin");
	}

	public void ReplicacaoIniciar() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				while (!Thread.currentThread().isInterrupted() ) {
					
					try {
							
						TableReplicationDAO ReplicationDAO = new TableReplicationDAO(conn);
												
						for(int row = 0; ReplicationDAO.Select(io_window.getProcess(), row) != null; row++) {
							
							switch(ReplicationDAO.Select(io_window.getProcess(), row).getTabela_destino()){
								
							case "Alunos":
								
								ReplicateStudents();
								alunos++;
								break;
								
							case "Planos":
								
								ReplicatePlans();
								planos++;
								break;
								
							case "Graduacoes":
								
								ReplicateGraduation();
								graduacoes++;
								break;
								
							case "Modalidades":
								
								ReplicateModality();
								modalidades++;
								break;
								
							case "Usuarios":
								
								ReplicateUsers();
								usuarios++;
								break;
																						
							}
							
						}
						
						JOptionPane.showMessageDialog(io_window, "Sucesso!", "Alerta:", JOptionPane.INFORMATION_MESSAGE);				
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}			
			}
		}).start();
		
	}

	public int ReplicateStudents() throws SQLException {

		AlunoDAO origin_dao = new AlunoDAO(conn_origin);
		AlunoDAO destiny_dao = new AlunoDAO(conn_destiny);

		List<Aluno> List = origin_dao.SelectAll("");

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicateUsers() throws SQLException {

		UsuarioDAO origin_dao = new UsuarioDAO(conn_origin);
		UsuarioDAO destiny_dao = new UsuarioDAO(conn_destiny);

		List<Usuario> List = origin_dao.SelectAll("");

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicatePlans() throws SQLException {

		PlanosDAO origin_dao = new PlanosDAO(conn_origin);
		PlanosDAO destiny_dao = new PlanosDAO(conn_destiny);

		List<Plano> List = origin_dao.SelectAll("");

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicateModality() throws SQLException {

		ModalidadesDAO origin_dao = new ModalidadesDAO(conn_origin);
		ModalidadesDAO destiny_dao = new ModalidadesDAO(conn_destiny);

		List<Modalidades> List = origin_dao.SelectAll("");

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicateGraduation() throws SQLException {

		GraduacoesDAO origin_dao = new GraduacoesDAO(conn_origin);
		GraduacoesDAO destiny_dao = new GraduacoesDAO(conn_destiny);

		List<Graduacoes> List = origin_dao.SelectAll("");

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

}
