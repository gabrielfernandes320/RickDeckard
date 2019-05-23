package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ConectionsReplicationDAO extends MasterDAO {

    private String is_select = "select codigo_replicacao,nome,endereco,porta,database,tipo_banco,url from TB_REPLICACAO where codigo_replicacao = ?";

    private String is_insert = "INSERT INTO TB_REPLICACAO			"
            +"(nome,"+
            "endereco," +
            "porta," +
            "database," +
            "tipo_banco," +
            "url)"+
            "VALUES("+
            "?,?,?,?,?) ";

    private PreparedStatement pst_select;
    private PreparedStatement pst_insert;

    @Override
    public List<Object> SelectAll() throws SQLException {
        return null;
    }

    @Override
    public Object Select(Object parameter) throws SQLException {


        return null;
    }

    @Override
    public void Update(Object parameter) throws SQLException {

    }

    @Override
    public void Insert(Object parameter) throws SQLException {

    }

    @Override
    public int Delete(Object parameter) throws SQLException {
        return 0;
    }
}
