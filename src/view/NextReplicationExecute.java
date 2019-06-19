package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.AlunoDAO;
import database.AssiduidadeDAO;
import database.CitiesDAO;
import database.ConectionsReplicationDAO;
import database.ConnectionFactory;
import database.GraduacoesDAO;
import database.InvoiceDAO;
import database.MatriculaDAO;
import database.Matricula_ModalidadeDAO;
import database.ModalidadesDAO;
import database.PlanosDAO;
import database.TableReplicationDAO;
import database.TableReplicationDirectionDAO;
import database.TableReplicationProcessDAO;
import database.UsuarioDAO;
import model.Aluno;
import model.Assiduidade;
import model.Cidade;
import model.ConectionReplication;
import model.Graduacoes;
import model.Invoice;
import model.Matricula;
import model.Matricula_Modalidade;
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

	private int alunos = 0 ;
	private int planos = 0;
	private int graduacoes= 0;
	private int modalidades= 0;
	private int usuarios= 0;
	private int assiduidade= 0;
	private int matriculas= 0;
	private int matriculas_modalidades= 0;
	private int cidades= 0;
	private int faturas_matriculas= 0;
	
	private int progress = 0;
	private int progressPerTable;
	private int lines_changed;

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
							
						lines_changed = 0;
						
						TableReplicationDAO ReplicationDAO = new TableReplicationDAO(conn);
												
						for(int row = 0; ReplicationDAO.Select(io_window.getProcess(), row) != null; row++) {
							
							switch(ReplicationDAO.Select(io_window.getProcess(), row).getTabela_destino()){
								
							case "alunos":
								
								alunos++;
								progress++;
								break;
								
							case "planos":
								
								planos++;
								progress++;
								break;
								
							case "graduacoes":
								
								graduacoes++;
								progress++;
								break;
								
							case "modalidades":
								
								modalidades++;
								progress++;
								break;
								
							case "usuarios":
								
								usuarios++;
								progress++;
								break;
								
							case "assiduidade":
								
								assiduidade++;
								progress++;
								break;		
								
							case "matriculas":
								
								matriculas++;
								progress++;
								break;
						
							case "matriculas_modalidades":
								
								matriculas_modalidades++;
								progress++;
								break;
								
							case "faturas_matriculas:":
								faturas_matriculas++;
								progress++;
								break;
								
							case "cidades":
								cidades++;
								progress++;
								break;
							default:								
								
							}
																																								
						}
																		
						progressPerTable = 100/progress;	
						progress = 0;
						io_window.setProgress(progress);
						
						if(alunos != 0) {
							
							lines_changed = lines_changed + ReplicateStudents();
							
							io_window.setProgress(progress);
							
						}
						
						if(planos != 0) {
							
							lines_changed = lines_changed + ReplicatePlans();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(graduacoes != 0) {
							
							lines_changed = lines_changed + ReplicateGraduation();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(modalidades != 0) {
							
							lines_changed = lines_changed + ReplicateModality();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(usuarios != 0) {
							
							lines_changed = lines_changed + ReplicateUsers();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(assiduidade != 0) {
							
							lines_changed = lines_changed + ReplicateAssiduidade();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(matriculas != 0) {
							
							lines_changed = lines_changed + ReplicateMatriculas();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(matriculas_modalidades != 0) {
							
							lines_changed = lines_changed + ReplicateMatriculas_modalidades();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(faturas_matriculas != 0) {
							
							lines_changed = lines_changed + ReplicateFaturas_matriculas();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
						}
						
						if(cidades != 0) {
							
							lines_changed = lines_changed + ReplicateCidade();
							progress = progress + progressPerTable;
							io_window.setProgress(progress);
							
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
	
	public int ReplicateAssiduidade() throws SQLException {

		AssiduidadeDAO origin_dao = new AssiduidadeDAO(conn_origin);
		AssiduidadeDAO destiny_dao = new AssiduidadeDAO(conn_destiny);

		List<Assiduidade> List = origin_dao.SelectAllP(1);

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}
	
	public int ReplicateMatriculas() throws SQLException {

		MatriculaDAO origin_dao = new MatriculaDAO(conn_origin);
		MatriculaDAO destiny_dao = new MatriculaDAO(conn_destiny);

		List<Matricula> List = origin_dao.SelectAll(1);

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicateMatriculas_modalidades() throws SQLException {

		Matricula_ModalidadeDAO origin_dao = new Matricula_ModalidadeDAO(conn_origin);
		Matricula_ModalidadeDAO destiny_dao = new Matricula_ModalidadeDAO(conn_destiny);

		List<Matricula_Modalidade> List = origin_dao.SelectAllP(1);

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicateFaturas_matriculas() throws SQLException {

		InvoiceDAO origin_dao = new InvoiceDAO(conn_origin);
		InvoiceDAO destiny_dao = new InvoiceDAO(conn_destiny);

		List<Invoice> List = origin_dao.SelectAllP(1);

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}
	
	public int ReplicateCidade() throws SQLException {

		CitiesDAO origin_dao = new CitiesDAO(conn_origin);
		CitiesDAO destiny_dao = new CitiesDAO(conn_destiny);

		List<Cidade> List = origin_dao.SelectAllP(1);

		int Rows = 0;

		while (List.get(Rows) != null) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}
	
}
