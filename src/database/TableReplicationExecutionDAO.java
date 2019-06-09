package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TableReplicationExecutionDAO extends MasterDAO {
	
	private String is_selectAll = "select * from tb_replicacao_tabela_execucao";
	private String is_select = "select * from tb_replicacao_tabela_execucao where codigo_tabela = ? order by codigo_tabela";
	private String is_insert = "INSERT INTO tb_replicacao_tabela_execucao			"
								+ "(							"
								+ "	codigo_tabela,				"
								+ "	data_atual,					"
								+ "	processo,					"
								+ " database__origem,			"
								+ " usuario_origem,				"
								+ " database_destino,			"
								+ " usuario_destino,			"
								+ " execucao_inicio_data_hora,	"
								+ " ordem,						"
								+ " inicio_data_hora,			"
								+ " fim_data_hora,				"
								+ " data_atual_ate,				"
								+ " linhas_processadas,			"
								+ " sucesso,					"
								+ " mensagem					"
								+ ")							"
								+ "VALUES						"
								+ "(							"
								+ " default,					"
								+ " ?,?,?,?,?,?,?,?,?,?,?,?,?,?"
								+ ") ";

	
	private String is_update = "";
	
	private String is_delete = "DELETE * FROM tb_replicacao_tabela_execucao WHERE codigo_tabela = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public TableReplicationExecutionDAO(Connection connection) throws SQLException {
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);
		pst_update = connection.prepareStatement(is_update);
	}

	@Override
	public List<Object> SelectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
