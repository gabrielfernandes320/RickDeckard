package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
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
import database.TableReplicationExecutionDAO;
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
import model.tbReplicacaoHistorico;

public class NextReplicationExecute {

	private NextWindowReplication io_window;
	private ConectionReplication origin_connection = null;
	private ConectionReplication destiny_connection = null;
	private Connection conn;
	private Connection conn_origin = null;
	private Connection conn_destiny = null;

	private int alunos = 0;
	private int planos = 0;
	private int graduacoes = 0;
	private int modalidades = 0;
	private int usuarios = 0;
	private int assiduidade = 0;
	private int matriculas = 0;
	private int matriculas_modalidades = 0;
	private int cidades = 0;
	private int faturas_matriculas = 0;

	private int progress = 0;
	private int progressPerTable;
	private int lines_changed;

	public NextReplicationExecute(NextWindowReplication window) throws SQLException {

		// Guardo a referencia.
		io_window = window;
		io_window.getProcess();

		// Instancia os DAO de controle.
		SetConections();

	}

	public void SetConections() throws SQLException {

		conn = ConnectionFactory.getConnection("nextDB", "admin", "admin");
		conn.setAutoCommit(false);
		TableReplicationDirectionDAO DirectionDAO = new TableReplicationDirectionDAO(conn);
		ConectionsReplicationDAO ConnectionsDAO = new ConectionsReplicationDAO(conn);

		// Guardo a conexão.
		String DB_Origem = DirectionDAO.selectOrigem(io_window.getDirection());
		origin_connection = (ConectionReplication) ConnectionsDAO.Select(DB_Origem);

		String DB_Destino = DirectionDAO.selectDestino(io_window.getDirection());
		destiny_connection = (ConectionReplication) ConnectionsDAO.Select(DB_Destino);

		conn_origin = ConnectionFactory.getConnection(origin_connection.getConnectionAddress(),
				origin_connection.getConnectionPort(), origin_connection.getDatabaseSID(), "admin", "admin");
		conn_destiny = ConnectionFactory.getConnection(destiny_connection.getConnectionAddress(),
				destiny_connection.getConnectionPort(), destiny_connection.getDatabaseSID(), "admin", "admin");

	}

	boolean matarThread = false;

	public void ReplicacaoIniciar() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (!matarThread) {

					try {

						tbReplicacaoHistorico historico = new tbReplicacaoHistorico();

						historico.setInicio_data_hora(new Timestamp(System.currentTimeMillis()));
						historico.setProcesso(io_window.getProcess());
						historico.setDatabase_origem(origin_connection.getDatabaseSID());
						historico.setDatabase_destino(destiny_connection.getDatabaseSID());
						historico.setUsuario("admin");
						historico.setUsuario_origem("admin");
						historico.setUsuario_destino("admin");
						historico.setOcorrencia("Tabelas copiadas:");

						lines_changed = 0;

						TableReplicationDAO ReplicationDAO = new TableReplicationDAO(conn);

						int LastOrdem = ReplicationDAO.SelectLastOrdem(io_window.getProcess()).getOrdem();

						int row = 0;

						while (row <= LastOrdem) {

							String tabela = ReplicationDAO.Select(io_window.getProcess(), row).getTabela_destino();

							if (tabela.equals("alunos") || tabela.equals("Alunos")) {

								alunos = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Alunos");

							}

							else if (tabela.equals("planos") || tabela.equals("Planos")) {

								planos = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Planos");

							}

							else if (tabela.equals("graduacoes") || tabela.equals("Graduacoes")) {

								graduacoes = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Graduacoes");

							}

							else if (tabela.equals("modalidades") || tabela.equals("Modalidades")) {

								modalidades = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Modalidades");

							}

							else if (tabela.equals("usuarios") || tabela.equals("Usuarios")) {

								usuarios = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Usuarios");

							}

							else if (tabela.equals("assiduidade") || tabela.equals("Assiduidade")) {

								assiduidade = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Assiduidade");

							}

							else if (tabela.equals("matriculas") || tabela.equals("Matriculas")) {

								matriculas = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Matriculas");

							}

							else if (tabela.equals("matriculas_modalidades")
									|| tabela.equals("Matriculas_modalidades")) {

								matriculas_modalidades = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Matriculas_modalidades");

							}

							else if (tabela.equals("faturas_matriculas") || tabela.equals("Faturas_matriculas")) {

								faturas_matriculas = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Faturas_matriculas");

							}

							else if (tabela.equals("cidades") || tabela.equals("Cidades")) {

								cidades = row;
								progress++;

								historico.setOcorrencia(historico.getOcorrencia() + "/Cidades");

							}

							row++;

						}

						JOptionPane.showMessageDialog(io_window, "Iniciando!", "Alerta:",
								JOptionPane.INFORMATION_MESSAGE);

						progressPerTable = 100 / progress;
						progress = 0;
						io_window.setProgress(progress);
						
						row = 0;
						
						while (row <= LastOrdem) {
							
							if (alunos == row) {

								lines_changed = lines_changed + ReplicateStudents();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);
								
								row++;

							}

							if (planos == row) {

								lines_changed = lines_changed + ReplicatePlans();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);

								row++;
								
							}

							if (graduacoes == row) {

								lines_changed = lines_changed + ReplicateGraduation();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);

								row++;
								
							}

							if (modalidades == row) {

								lines_changed = lines_changed + ReplicateModality();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);

								row++;
								
							}

							if (usuarios == row) {

								lines_changed = lines_changed + ReplicateUsers();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);

								row++;
								
							}
					
							if (assiduidade == row) {

								lines_changed = lines_changed + ReplicateAssiduidade();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);

								row++;
								
							}

							if (matriculas == row) {

								lines_changed = lines_changed + ReplicateMatriculas();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);
								
								row++;

							}

							if (matriculas_modalidades == row) {

								lines_changed = lines_changed + ReplicateMatriculas_modalidades();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);
								
								row++;

							}

							if (faturas_matriculas == row) {

								lines_changed = lines_changed + ReplicateFaturas_matriculas();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);
								
								row++;

							}

							if (cidades == row) {

								lines_changed = lines_changed + ReplicateCidade();
								progress = progress + progressPerTable;
								io_window.setProgress(progress);
								
								row++;

							}
							
						}
						
						historico.setFim_data_hora(new Timestamp(System.currentTimeMillis()));
						TableReplicationExecutionDAO historicoDAO = new TableReplicationExecutionDAO(conn);
						historicoDAO.Insert(historico);

						JOptionPane.showMessageDialog(io_window, "Sucesso!", "Alerta:", JOptionPane.INFORMATION_MESSAGE);
						matarThread = true;

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

		conn_destiny.setAutoCommit(false);

		for (Aluno aluno : List) {

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

		conn_destiny.setAutoCommit(false);

		for (Usuario usuario : List) {

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

		conn_destiny.setAutoCommit(false);

		for (Plano plano : List)

		{

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

		conn_destiny.setAutoCommit(false);

		for (Modalidades modalidades : List) {

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

		conn_destiny.setAutoCommit(false);

		for (Graduacoes graduacoes : List) {

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

		conn_destiny.setAutoCommit(false);

		for (Assiduidade assiduidade : List) {
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

		conn_destiny.setAutoCommit(false);

		for (Matricula matricula : List) {

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

		conn_destiny.setAutoCommit(false);

		for (Matricula_Modalidade matricula_Modalidade : List) {

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

		conn_destiny.setAutoCommit(false);

		for (Invoice invoice : List) {

			destiny_dao.Insert(List.get(Rows));

			Rows++;

		}

		return Rows;

	}

	public int ReplicateCidade() throws SQLException {

		CitiesDAO origin_dao = new CitiesDAO(conn_origin);
		CitiesDAO destiny_dao = new CitiesDAO(conn_destiny);

		List<Cidade> List = origin_dao.SelectAllP();

		int Rows = 0;

		conn_destiny.setAutoCommit(false);

		for (Cidade cidade : List) {

			destiny_dao.Insert(cidade);

			Rows++;

		}

		return Rows;

	}

}
