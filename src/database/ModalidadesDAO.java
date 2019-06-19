package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Modalidades;
import model.Plano;

public class ModalidadesDAO extends MasterDAO{
	
	// Cria as variaveis contendo o select a ser feito.
	private String is_delete = "delete from modalidades where modalidade = ?";
	private String is_selectAll = "select * from modalidades order by modalidade";
	private String is_selectAllModalidade = "select * from modalidades order by modalidade";
	private String is_selectAllPesquisa = "select * from modalidades where modalidade like ? order by modalidade";
	private String is_select = "select * from modalidades where modalidade = ? order by modalidade";
	private String is_insert = "INSERT INTO modalidades"
								+" (					"
								+"		modalidade		"
								+" )					"
								+"	VALUES				"
								+" (					"
								+"		?"
								+" )";
	private String is_update = "UPDATE public.modalidades\r\n" + 
							"   SET modalidade=?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_selectAllModalidade;
	private PreparedStatement pst_selectAllPesquisa;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public ModalidadesDAO(Connection connection) throws SQLException {
		
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_selectAllModalidade = connection.prepareStatement(is_selectAllModalidade);
		pst_selectAllPesquisa = connection.prepareStatement(is_selectAllPesquisa);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);	
		pst_delete = connection.prepareStatement(is_delete);
		pst_update = connection.prepareStatement(is_update);
		
		
	}
	
	public List<Modalidades> SelectAll(final String pesquisa) throws SQLException {
		List<Modalidades> arlModalidade = new ArrayList<Modalidades>();
		
		Set(pst_selectAllPesquisa, 1, "%"+pesquisa+"%");
		
		ResultSet rst = pst_selectAllPesquisa.executeQuery();
		 
		while(rst.next()){
			 Modalidades model = new Modalidades();
			 
			 model.setModalidade(rst.getString("modalidade"));
			  arlModalidade.add(model);
		 }
				
		return arlModalidade;
	}
	

	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> arlModalidade = new ArrayList<Object>();
		ResultSet rst = pst_selectAll.executeQuery();
		 
		while(rst.next()){
			 Modalidades model = new Modalidades();
			 
			 model.setModalidade(rst.getString("modalidade"));
			 arlModalidade.add(model);
		 }
				
		return arlModalidade;
	}
	public String[] SelectAllModalidade() throws SQLException {
		ArrayList<String> arlPlano = new ArrayList<String>();
		String[] returno = null;
		
		ResultSet rst = pst_selectAllModalidade.executeQuery();
		 
		while(rst.next()){
			 Plano model = new Plano();
			 
			 model.setModalidade(rst.getString("modalidade"));
			 
			 arlPlano.add(model.getModalidade());
		 }
		returno = new String[arlPlano.size()];
		return arlPlano.toArray(returno);
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
//		cria o objeto modaliidade
		Modalidades modalidade = null;
		
		Set(pst_select, 1, ((Modalidades)parameter).getModalidade());
		
		ResultSet rst = pst_select.executeQuery();
		if(rst.next()) {
			modalidade = new Modalidades();
			modalidade.setModalidade(rst.getString("modalidade"));
		}
		return modalidade;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Modalidades lo_modalidade = (Modalidades)parameter;
		Set(pst_update, 1, lo_modalidade.getModalidade());
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			io_connection.commit();
		}
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Modalidades lo_modalidade = (Modalidades)parameter;
		Set(pst_insert, 1, lo_modalidade.getModalidade());
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		int affectedrows = 0;

		Modalidades lo_modalidade = (Modalidades)parameter;

        Set(pst_delete, 1, lo_modalidade.getModalidade());

        affectedrows = pst_delete.executeUpdate();
        io_connection.commit();
        
        return affectedrows;

	}
	
	

}
