package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Modalidades;
import model.TableReplication;
import model.TableReplicationExecution;

public class TableReplicationProcessDAO extends MasterDAO {

	private String is_selectAll = "select * from tb_replicacao_processo";
	private String is_select = "select * from tb_replicacao_processo where processo like ? order by codigo_processo";
	private String is_insert = "INSERT INTO tb_replicacao_processo (\r\n" + 
			"            codigo_processo, data_atual, usuario, processo, descricao, data_atual_de, \r\n" + 
			"            erro_ignorar, habilitado)\r\n" + 
			"    VALUES (default, default, ?, ?, ?, current_date, \r\n" + 
			"            ?, ?);\r\n" + 
			"";

	private String is_update = "";

	private String is_delete = "DELETE * FROM tb_replicacao_processo WHERE codigo_processo = ?";

	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;

	Connection io_connection;

	public TableReplicationProcessDAO(Connection connection) throws SQLException {
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);
		pst_update = connection.prepareStatement(is_update);
	}

	public List<TableReplication> SelectAll(final String pesquisa) throws SQLException {
		List<TableReplication> arlProccess = new ArrayList<TableReplication>();

		Set(pst_select, 1, "%" + pesquisa + "%");

		ResultSet rst = pst_select.executeQuery();

		while (rst.next()) {
			TableReplication model = new TableReplication();

			model.setProcesso(rst.getString("processo"));
			arlProccess.add(model);
		}

		return arlProccess;
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

	public String[] selectProcessNames() throws SQLException {

		ResultSet rst = pst_selectAll.executeQuery();
		ArrayList<String> list = new ArrayList<String>();

		list.add("");

		while (rst.next()) {

			list.add(rst.getString("processo"));

		}

		String[] Process = (String[]) list.toArray(new String[list.size()]);

		return Process;

	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void Insert(String usuario, String processo, String descricao) throws SQLException {

		pst_insert.clearParameters();

		Set(pst_insert, 1, usuario);
		Set(pst_insert, 2, processo);
		Set(pst_insert, 3, descricao);
		Set(pst_insert, 4, "s");
		Set(pst_insert, 5, "s");

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

	@Override
	public void Insert(Object parameter) throws SQLException {
		// TODO Auto-generated method stub

	}

}
