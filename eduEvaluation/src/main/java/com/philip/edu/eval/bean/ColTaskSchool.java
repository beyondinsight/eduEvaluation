package com.philip.edu.eval.bean;

public class ColTaskSchool {
	private int id;
	private int task_id;
	private int school_id;
	private String school_code;
	private String school_name; 
	private char process_status;
	
	public char getProcess_status() {
		return process_status;
	}
	public void setProcess_status(char process_status) {
		this.process_status = process_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchool_code() {
		return school_code;
	}
	public void setSchool_code(String school_code) {
		this.school_code = school_code;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	
	
}
