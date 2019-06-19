package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TableReplication;
import model.TableReplicationExecution;
import model.TbTableReplication;
import model.Usuario;

public class TableReplicationDAO extends MasterDAO {
	
	private String is_selectAll = "select * from tb_replicacao_tabela";
	private String is_select = "select * from tb_replicacao_tabela where processo like ?, ordem like ? ";
	private String is_insert = "INSERT INTO tb_replicacao_tabela			"
								+ "(							"
								+ "	usuario,					"
								+ " processo,					"
								+ " ordem,						"
								+ " tabela_origem,				"
								+ " tabela_destino,				"
								+ " coluna_tipo,				"
								+ " coluna_chave,				"
								+ "linhas_maximo				"
								+ ")							"
								+ "VALUES						"
								+ "(							"
								+ "?,?,?,?,?,?,?,999999999"
								+ ") ";

	
	private String is_update = "";
	
	private String is_delete = "DELETE * FROM tb_replicacao_tabela WHERE codigo_processo = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public TableReplicationDAO(Connection connection) throws SQLException {
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);
		pst_update = connection.prepareStatement(is_update);
	}


	public TbTableReplication Select(String parameter, int order) throws SQLException {
		
		TbTableReplication model = new TbTableReplication();
		
		pst_select.setString(1, parameter);
		pst_select.setInt(2, order);
			
		ResultSet rst = pst_select.executeQuery();
		
		while (rst.next()) {
			
			model.setOrdem(rst.getInt("ordem"));
			model.setTabela_destino(rst.getString("tabela_destino"));
			
		}
		
		return model;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		TbTableReplication lo_tableReptab = (TbTableReplication)parameter;
		
		Set(pst_insert, 1, lo_tableReptab.getUsuario());
		Set(pst_insert, 2, lo_tableReptab.getProcesso());
		Set(pst_insert, 3, lo_tableReptab.getOrdem());
		Set(pst_insert, 4, lo_tableReptab.getTabela_origem());
		Set(pst_insert, 5, lo_tableReptab.getTabela_destino());
		Set(pst_insert, 6, lo_tableReptab.getColuna_tipo());
		Set(pst_insert, 7, lo_tableReptab.getColuna_chave());
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
		
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		int affectedrows = 0;

		TbTableReplication lo_tableReptab = (TbTableReplication)parameter;

        Set(pst_delete, 1, lo_tableReptab.getCodigo_replicacao());

        affectedrows = pst_delete.executeUpdate();
        io_connection.commit();
        
        return affectedrows;


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

}
