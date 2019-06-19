package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import model.ConectionReplication;

public class TableReplicationDirectionDAO extends MasterDAO {

    private String is_select = "select * from tb_replicacao_direcao where nome = ?";
    
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
    
    private String is_delete = "DELETE FROM public.tb_replicacao WHERE nome = '1?' ";

    private PreparedStatement pst_select;
    private PreparedStatement pst_selectDirectionNames;
    private PreparedStatement pst_insert;
    private PreparedStatement pst_delete;
    

    Connection io_connection;

    public TableReplicationDirectionDAO(Connection connection)
            throws SQLException {
        io_connection = connection;
        pst_select = connection.prepareStatement(is_select);
        pst_insert = connection.prepareStatement(is_insert);
        pst_selectDirectionNames = connection.prepareStatement(is_selectDirectionNames);
        //pst_drop_role = connection.prepareStatement(is_drop_role);
        //pst_delete = connection.prepareStatement(is_delete);
    }


    @Override
    public List<Object> SelectAll() throws SQLException {
        return null;
    }

    @Override
    public Object Select(Object parameter) throws SQLException {
        ConectionReplication connRep = null;

        // Seta os parametros.
        
        Set(pst_select, 1, ((ConectionReplication) parameter).getConnectionName());

      //is_select.replace("1?", ((ConectionReplication) parameter).getConnectionName());
      //pst_select = io_connection.prepareStatement(is_select);
        ResultSet rst = pst_select.executeQuery();

        if (rst.next()) {
            connRep = new ConectionReplication();
       
            connRep.setReplicationCode(rst.getString("codigo_replicacao"));
            connRep.setConnectionName(rst.getString("nome"));
            connRep.setConnectionAddress(rst.getString("endereco"));
            connRep.setConnectionPort(rst.getInt("porta"));
            connRep.setDatabaseSID("database");

            return connRep;

        }
        return null;
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
}