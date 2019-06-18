package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Service.Mode;

import com.sun.media.sound.ModelAbstractChannelMixer;

import model.Graduacoes;
import model.Modalidades;
import model.TableReplicationExecution;

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
	
		List<Object> tableRepExec = new ArrayList<Object>();
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			
			TableReplicationExecution  model = new TableReplicationExecution();
			
			model.setTable_Code(rst.getInt("codigo_tabela"));
			model.setCurren_tDate(rst.getTimestamp("data_atual"));
			model.setProcess(rst.getString("processo"));
			model.setSource_Database(rst.getString("database__origem"));
			model.setSource_User(rst.getString("usuario_origem"));
			model.setDestination_database("database_destino");
			model.setDestination_user(rst.getString("usuario_destino"));
			model.setExecution_start_date_hour(rst.getTimestamp("execucao_inicio_data_hora"));
			model.setOrder(rst.getInt("ordem"));
			model.setStart_date_hour(rst.getTimestamp("inicio_data_hora"));
			model.setEnd_date_hour(rst.getTimestamp("fim_data_hora"));
			model.setDate_current_to(rst.getTimestamp("data_atual_ate"));
			model.setProcess_lines(rst.getInt("linhas_processadas"));
			model.setSuccess(rst.getString("sucesso"));
			model.setMessage(rst.getString("mensagem"));
			
			tableRepExec.add(model);
		}
		return tableRepExec;
	
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		TableReplicationExecution tableRepExec = null;
		
		Set(pst_select, 1,((TableReplicationExecution)parameter).getTable_Code());
		ResultSet rst = pst_select.executeQuery();
		
		if(rst.next()) {
			TableReplicationExecution  model = new TableReplicationExecution();
			
			model.setTable_Code(rst.getInt("codigo_tabela"));
			model.setCurren_tDate(rst.getTimestamp("data_atual"));
			model.setProcess(rst.getString("processo"));
			model.setSource_Database(rst.getString("database__origem"));
			model.setSource_User(rst.getString("usuario_origem"));
			model.setDestination_database("database_destino");
			model.setDestination_user(rst.getString("usuario_destino"));
			model.setExecution_start_date_hour(rst.getTimestamp("execucao_inicio_data_hora"));
			model.setOrder(rst.getInt("ordem"));
			model.setStart_date_hour(rst.getTimestamp("inicio_data_hora"));
			model.setEnd_date_hour(rst.getTimestamp("fim_data_hora"));
			model.setDate_current_to(rst.getTimestamp("data_atual_ate"));
			model.setProcess_lines(rst.getInt("linhas_processadas"));
			model.setSuccess(rst.getString("sucesso"));
			model.setMessage(rst.getString("mensagem"));
			
			tableRepExec = model;
		
		}
		return tableRepExec;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		TableReplicationExecution lo_tableRepExec = (TableReplicationExecution)parameter;
		
		Set(pst_insert, 1, lo_tableRepExec.getTable_Code());
		Set(pst_insert, 2, lo_tableRepExec.getCurren_tDate());
		Set(pst_insert, 2, lo_tableRepExec.getProcess());
		Set(pst_insert, 2, lo_tableRepExec.getProcess());
		Set(pst_insert, 2, lo_tableRepExec.getSource_Database());
		Set(pst_insert, 2, lo_tableRepExec.getSource_User());
		Set(pst_insert, 2, lo_tableRepExec.getDestination_database());
		Set(pst_insert, 2, lo_tableRepExec.getDestination_user());
		Set(pst_insert, 2, lo_tableRepExec.getExecution_start_date_hour());
		Set(pst_insert, 2, lo_tableRepExec.getOrder());
		Set(pst_insert, 2, lo_tableRepExec.getStart_date_hour());
		Set(pst_insert, 2, lo_tableRepExec.getEnd_date_hour());
		Set(pst_insert, 2, lo_tableRepExec.getDate_current_to());
		Set(pst_insert, 2, lo_tableRepExec.getProcess_lines());
		Set(pst_insert, 2, lo_tableRepExec.getSuccess());
		Set(pst_insert, 2, lo_tableRepExec.getMessage());
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
