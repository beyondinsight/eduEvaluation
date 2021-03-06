package com.philip.edu.eval.bean;

import java.util.Date;

public class ColTaskMajor {
	private int id;
	private int collection_school_id;
	private int school_id;
	private int major_id;
	private String major_name;
	private String major_code;
	private Date create_time;
	private Date update_time;
	private char process_status;
	private String process_status_note;
	
	
	public String getProcess_status_note() {
		return process_status_note;
	}
	public void setProcess_status_note(String process_status_note) {
		this.process_status_note = process_status_note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCollection_school_id() {
		return collection_school_id;
	}
	public void setCollection_school_id(int collection_school_id) {
		this.collection_school_id = collection_school_id;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public char getProcess_status() {
		return process_status;
	}
	public void setProcess_status(char process_status) {
		this.process_status = process_status;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String getMajor_code() {
		return major_code;
	}
	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}
	
}
