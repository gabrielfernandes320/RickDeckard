package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.TableReplicationDAO;
import model.TableReplication;
import model.TbTableReplication;

public class TableReplicationTableModel extends AbstractTableModel {
	
	private List<TbTableReplication> tb_rep_exec;
	private String[] colunas = new String[] {"Tabela Origem"};
	
	public TableReplicationTableModel(List<TbTableReplication> tb_rep_exec) {
		this.tb_rep_exec = tb_rep_exec;
	}

	public TableReplicationTableModel() {
		this.tb_rep_exec = new ArrayList<TbTableReplication>();
	}

	public int getRowCount() {
		return tb_rep_exec.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;	}
	
	public void setValueAt(TbTableReplication aValue, int rowIndex) {
		TbTableReplication modal = tb_rep_exec.get(rowIndex);

		modal.setTabela_origem(aValue.getTabela_origem());
		modal.setOrdem(aValue.getOrdem());
		modal.setTabela_origem(aValue.getTabela_origem());
		modal.setTabela_destino(aValue.getTabela_destino());
		modal.setColuna_chave(aValue.getColuna_chave());
		modal.setColuna_tipo(aValue.getColuna_tipo());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);
		fireTableCellUpdated(rowIndex, 5);
		
	}
	
	
	public void setValueAt(TbTableReplication aValue, int rowIndex, int columnIndex) {
		TbTableReplication modal = tb_rep_exec.get(rowIndex);

		switch (columnIndex) {
		case 0:
			modal.setProcesso(aValue.getTabela_origem());
			break;
		case 1:
			modal.setOrdem(aValue.getOrdem());
			break;
		case 2:
			modal.setTabela_origem(aValue.getTabela_origem());
			break;
		case 3:	
			modal.setTabela_destino(aValue.getTabela_destino());
			break;
		case 4:
			modal.setColuna_chave(aValue.getColuna_chave());
			break;
		case 5:
			modal.setColuna_tipo(aValue.getColuna_tipo());
			break;
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}


	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		TbTableReplication modal = tb_rep_exec.get(rowIndex);
		TbTableReplication valueObject = new TbTableReplication();
		switch (columnIndex) {
		case 0:
			valueObject.setTabela_origem(modal.getTabela_origem());
			valueObject.setProcesso(modal.getProcesso());
			valueObject.setOrdem(modal.getOrdem());
			valueObject.setTabela_destino(modal.getTabela_destino());
			valueObject.setColuna_chave(modal.getColuna_chave());
			valueObject.setColuna_tipo(modal.getColuna_tipo());
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public TbTableReplication getProccess(int indiceLinha) {
		return tb_rep_exec.get(indiceLinha);
	}
	
	public void addProccess(TbTableReplication u) {
		tb_rep_exec.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProccess(int indiceLinha) {
		tb_rep_exec.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void addListaDeProcessos(List<TbTableReplication> novosProcessos) {

		int tamanhoAntigo = getRowCount();		
		tb_rep_exec = novosProcessos;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		tb_rep_exec.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return tb_rep_exec.isEmpty();
	}

		
}
