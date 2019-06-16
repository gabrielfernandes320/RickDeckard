package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import model.ConectionReplication;
import model.Usuario;

public class ConectionsReplicationDAO extends MasterDAO {

    private String is_select = "select codigo_replicacao,nome,endereco,porta,database,tipo_banco,url from TB_REPLICACAO where codigo_replicacao = ?";

    private String is_insert = "INSERT INTO public.TB_REPLICACAO			"
            + "(usuario,nome," +
            "endereco," +
            "porta," +
            "database," +
            "tipo_banco," +
            "url)" +
            "VALUES(" +
            "?,?,?,?,?,?,?) ";

    private PreparedStatement pst_select;
    private PreparedStatement pst_insert;

    Connection io_connection;

    public ConectionsReplicationDAO(Connection connection)
            throws SQLException {
        io_connection = connection;
        pst_select = connection.prepareStatement(is_select);
        pst_insert = connection.prepareStatement(is_insert);
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

        ResultSet rst = pst_select.executeQuery();

        if (rst.next()) {
            connRep = new ConectionReplication();
            connRep.setReplicationCode(rst.getString("code"));
            connRep.setConnectionAddress(rst.getString("address"));
            connRep.setConnectionName(rst.getString("name"));
            connRep.setConnectionPort(rst.getInt("port"));
            connRep.setDatabaseSID("dbSID");

            return connRep;

        }
        return null;
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
        return 0;
    }
}