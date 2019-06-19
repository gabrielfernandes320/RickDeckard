package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Assiduidade;
import model.Cidade;
import model.Invoice;
import model.Usuario;

public class CitiesDAO extends MasterDAO {


	private String is_selectAll = "SELECT * from cidades;";
	
	private String is_insert = "INSERT INTO public.cidades(cidade, estado, pais)  VALUES (?, ?, ?);";

	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_payment;
	private PreparedStatement pst_cancel;
	private PreparedStatement pst_changevalue;
	private PreparedStatement pst_selectAllPesquisa;

	Connection io_connection;

	public CitiesDAO(Connection connection) throws SQLException {

		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_insert = connection.prepareStatement(is_insert);

	}

	public List<Cidade> SelectAllP(final int pesquisa) throws SQLException {
		
		List<Cidade> arlCidade = new ArrayList<Cidade>();

		Set(pst_selectAllPesquisa, 1, pesquisa);

		ResultSet rst = pst_selectAllPesquisa.executeQuery();

		while (rst.next()) {
			
			Cidade model = new Cidade();

			model.setCidade(rst.getString("cidade"));
			model.setPais(rst.getString("pais"));
			model.setEstado(rst.getString("estado"));

			
			arlCidade.add(model);
			
		}

		return arlCidade;
	}

	public void Insert(Cidade parameter) throws SQLException {
		pst_insert.clearParameters();

		Cidade lo_cidade = (Cidade) parameter;

		Set(pst_insert, 1, lo_cidade.getCidade());
		Set(pst_insert, 2, lo_cidade.getEstado());
		Set(pst_insert, 3, lo_cidade.getPais());
		
		pst_insert.execute();

		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}

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
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}


}
