package com.philip.edu.eval.bean;

public class UserTask {

	private int task_id;
	private String task_name;
	private String task_status;
	private int task_year;
	private String user_id;

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTas_name(String tas_name) {
		this.task_name = tas_name;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public int getTask_year() {
		return task_year;
	}

	public void setTask_year(int task_year) {
		this.task_year = task_year;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
