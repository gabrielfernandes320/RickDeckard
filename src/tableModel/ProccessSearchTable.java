package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.TableReplicationExecution;

public class ProccessSearchTable extends AbstractTableModel {
	
	private List<TableReplicationExecution> tb_rep_exec;
	private String[] colunas = new String[] {"Processos"};
	
	public ProccessSearchTable(List<TableReplicationExecution> tb_rep_exec) {
		this.tb_rep_exec = tb_rep_exec;
	}

	public ProccessSearchTable() {
		this.tb_rep_exec = new ArrayList<TableReplicationExecution>();
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
	
	public void setValueAt(TableReplicationExecution aValue, int rowIndex) {
		TableReplicationExecution modal = tb_rep_exec.get(rowIndex);

		modal.setProcess(aValue.getProcess());
		
		fireTableCellUpdated(rowIndex, 0);
	}
	
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		TableReplicationExecution modal = tb_rep_exec.get(rowIndex);

		switch (columnIndex) {
		case 0:
			modal.setProcess(aValue.toString());
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}


	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		TableReplicationExecution modal = tb_rep_exec.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = modal.getProcess();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public TableReplicationExecution getProccess(int indiceLinha) {
		return tb_rep_exec.get(indiceLinha);
	}
	
	public void addProccess(TableReplicationExecution u) {
		tb_rep_exec.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProccess(int indiceLinha) {
		tb_rep_exec.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void addListaDeProcessos(List<TableReplicationExecution> novosProcessos) {

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
