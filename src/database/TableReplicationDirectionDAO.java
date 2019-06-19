package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import model.ConectionReplication;

public class TableReplicationDirectionDAO extends MasterDAO {

    private String is_select = "select database_origem, database_destino from tb_replicacao_direcao where nome = ?";
    
    private String is_selectOrigem = "select database_origem from tb_replicacao_direcao where nome = ?";
    
    private String is_selectDestino = "select database_destino from tb_replicacao_direcao where nome = ?";
    
    private String is_selectDirectionNames = "select codigo_direcao from tb_replicacao_direcao where processo = ?";

    private String is_insert = "INSERT INTO public.TB_REPLICACAO			"
            + "(usuario,nome," +
            "endereco," +
            "porta," +
            "database," +
            "tipo_banco," +
            "url)" +
            "VALUES(" +
            "?,?,?,?,?,?,?) ";
    
	private String is_insertDirecao = "insert into TB_REPLICACAO_DIRECAO "
    		+ "(codigo_direcao, data_atual, processo, database_origem, database_destino,"
    		+ "usuario_origem, usuario_destino, senha_origem, senha_destino,"
    		+ "automatico_manual, periodo_ano, periodo_mes, periodo_dia,"
    		+ "periodo_hora, periodo_minuto, periodo_segundo, retencao, habilitado)"
    		+ "values (default, current_date, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
    private String is_delete = "DELETE FROM public.tb_replicacao WHERE nome = '1?' ";

    private PreparedStatement pst_select;
    private PreparedStatement pst_selectDirectionNames;
    private PreparedStatement pst_insert;
	private PreparedStatement pst_insertDirecao;
    private PreparedStatement pst_delete;
    private PreparedStatement pst_selectOrigem;
    private PreparedStatement pst_selectDestino;
    

    Connection io_connection;

    public TableReplicationDirectionDAO(Connection connection)
            throws SQLException {
        io_connection = connection;
        pst_select = connection.prepareStatement(is_select);
        pst_insert = connection.prepareStatement(is_insert);
		pst_insertDirecao = connection.prepareStatement(is_insertDirecao);
        pst_selectDirectionNames = connection.prepareStatement(is_selectDirectionNames);
        pst_selectOrigem = connection.prepareStatement(is_selectOrigem);
        pst_selectDestino = connection.prepareStatement(is_selectDestino);
    }


    @Override
    public List<Object> SelectAll() throws SQLException {
        return null;
    }

    public String[] Select(String nome) throws SQLException {
    	
		pst_selectDirectionNames.setString(1, nome);
		
		ResultSet rst = pst_selectDirectionNames.executeQuery();	
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("");
		
		while (rst.next()) {
			
			list.add (rst.getString ("codigo_direcao"));
			
		}
		
		String[] Direction = (String[]) list.toArray (new String[list.size()]);
		
		return Direction;
    }

	public String[] selectDirectionNames(String processo) throws SQLException {
		
		pst_selectDirectionNames.setString(1, processo);
		
		ResultSet rst = pst_selectDirectionNames.executeQuery();	
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("");
		
		while (rst.next()) {
			
			list.add (rst.getString ("codigo_direcao"));
			
		}
		
		String[] Direction = (String[]) list.toArray (new String[list.size()]);
		
		return Direction;
		
	}
    
	public String selectOrigem(String nome) throws SQLException {
		
		pst_selectOrigem.setString(1, nome);
		
		ResultSet rst = pst_selectOrigem.executeQuery();	
		
		String origem = rst.getString ("database_origem");
		
		return origem;
		
	}
	
	public String selectDestino(String nome) throws SQLException {
		
		pst_selectDestino.setString(1, nome);
		
		ResultSet rst = pst_selectDestino.executeQuery();	
		
		String destino = rst.getString ("database_destino");
		
		return destino;
		
	}
			
    @Override
    public void Update(Object parameter) throws SQLException {

    }

    @Override
    public void Insert(Object parameter) throws SQLException {

		pst_insert.clearParameters();
		
		ConectionReplication lo_replication = (ConectionReplication)parameter;
		
		Set(pst_insert, 1, lo_replication.getUser());
		Set(pst_insert, 2, lo_replication.getConnectionName());
		Set(pst_insert, 3, lo_replication.getConnectionAddress());
		Set(pst_insert, 4, lo_replication.getConnectionPort());
		Set(pst_insert, 5, lo_replication.getDatabaseSID());
		Set(pst_insert, 6, lo_replication.getDatabaseType());
		Set(pst_insert, 7, lo_replication.getDatabaseURL());
		
		
		pst_insert.execute();
		

    }

	public void InsertDirecao(Object parameter) throws SQLException {

		pst_insertDirecao.clearParameters();
		
		ReplicationDirection lo_replication = (ReplicationDirection)parameter;
		
		
		Set(pst_insertDirecao, 1, lo_replication.getProcesso());
		Set(pst_insertDirecao, 2, lo_replication.getDatabase_origem());
		Set(pst_insertDirecao, 3, lo_replication.getDatabase_destino());
		Set(pst_insertDirecao, 4, lo_replication.getUsuario_origem());
		Set(pst_insertDirecao, 5, lo_replication.getUsuario_destino());
		Set(pst_insertDirecao, 6, lo_replication.getSenha_origem());
		Set(pst_insertDirecao, 7, lo_replication.getSenha_destino());
		Set(pst_insertDirecao, 8, lo_replication.getAuto_manual());
		Set(pst_insertDirecao, 9, lo_replication.getAno());
		Set(pst_insertDirecao, 10, lo_replication.getMes());
		Set(pst_insertDirecao, 11, lo_replication.getDia());
		Set(pst_insertDirecao, 12, lo_replication.getHora());
		Set(pst_insertDirecao, 13, lo_replication.getMinuto());
		Set(pst_insertDirecao, 14, lo_replication.getSegundo());
		Set(pst_insertDirecao, 15, lo_replication.getRetencao());
		Set(pst_insertDirecao, 16, lo_replication.getHabilitado());
		
		pst_insertDirecao.execute();
		

    }
	
    @Override
    public int Delete(Object parameter) throws SQLException {
    	int af;
    	ConectionReplication lo_replication = ((ConectionReplication)parameter);
		//pst_delete.setString(1, lo_usuario.getUsuario());
		is_delete = is_delete.replace("1?", lo_replication.getConnectionName());
		pst_delete = io_connection.prepareStatement(is_delete);
		af = pst_delete.executeUpdate();
		return af;
    }


	@Override
	public Object Select(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
