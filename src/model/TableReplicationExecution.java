package model;

import java.sql.Timestamp;

public class TableReplicationExecution {
	
	private int table_Code;
	private Timestamp curren_tDate;
	private String process;
	private String source_Database;
	private String source_User;
	private String destination_database;
	private String destination_user;
	private Timestamp execution_start_date_hour;
	private int order;
	private Timestamp start_date_hour;
	private Timestamp end_date_hour;
	private Timestamp date_current_to;
	private int process_lines;
	private char success;
	private String message;
	public int getTable_Code() {
		return table_Code;
	}
	public void setTable_Code(int table_Code) {
		this.table_Code = table_Code;
	}
	public Timestamp getCurren_tDate() {
		return curren_tDate;
	}
	public void setCurren_tDate(Timestamp curren_tDate) {
		this.curren_tDate = curren_tDate;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getSource_Database() {
		return source_Database;
	}
	public void setSource_Database(String source_Database) {
		this.source_Database = source_Database;
	}
	public String getSource_User() {
		return source_User;
	}
	public void setSource_User(String source_User) {
		this.source_User = source_User;
	}
	public String getDestination_database() {
		return destination_database;
	}
	public void setDestination_database(String destination_database) {
		this.destination_database = destination_database;
	}
	public String getDestination_user() {
		return destination_user;
	}
	public void setDestination_user(String destination_user) {
		this.destination_user = destination_user;
	}
	public Timestamp getExecution_start_date_hour() {
		return execution_start_date_hour;
	}
	public void setExecution_start_date_hour(Timestamp execution_start_date_hour) {
		this.execution_start_date_hour = execution_start_date_hour;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Timestamp getStart_date_hour() {
		return start_date_hour;
	}
	public void setStart_date_hour(Timestamp start_date_hour) {
		this.start_date_hour = start_date_hour;
	}
	public Timestamp getEnd_date_hour() {
		return end_date_hour;
	}
	public void setEnd_date_hour(Timestamp end_date_hour) {
		this.end_date_hour = end_date_hour;
	}
	public Timestamp getDate_current_to() {
		return date_current_to;
	}
	public void setDate_current_to(Timestamp date_current_to) {
		this.date_current_to = date_current_to;
	}
	public int getProcess_lines() {
		return process_lines;
	}
	public void setProcess_lines(int process_lines) {
		this.process_lines = process_lines;
	}
	public char getSuccess() {
		return success;
	}
	public void setSuccess(char success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TableReplicationExecution(int table_Code, Timestamp curren_tDate, String process, String source_Database,
			String source_User, String destination_database, String destination_user,
			Timestamp execution_start_date_hour, int order, Timestamp start_date_hour, Timestamp end_date_hour,
			Timestamp date_current_to, int process_lines, char success, String message) {
		super();
		this.table_Code = table_Code;
		this.curren_tDate = curren_tDate;
		this.process = process;
		this.source_Database = source_Database;
		this.source_User = source_User;
		this.destination_database = destination_database;
		this.destination_user = destination_user;
		this.execution_start_date_hour = execution_start_date_hour;
		this.order = order;
		this.start_date_hour = start_date_hour;
		this.end_date_hour = end_date_hour;
		this.date_current_to = date_current_to;
		this.process_lines = process_lines;
		this.success = success;
		this.message = message;
	}
	

}
