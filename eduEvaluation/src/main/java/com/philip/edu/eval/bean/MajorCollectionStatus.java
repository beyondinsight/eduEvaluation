package com.philip.edu.eval.bean;

public class MajorCollectionStatus {

	private int major_id;
	private int school_id;
	private int task_id;
	private String collection_template_name;
	private int collection_template_type;
	private String template_type_string;
	private String process_status;
	private int collection_major_id;
	private int process_status_key;
	private String process_status_note;
	
	
	public String getProcess_status_note() {
		return process_status_note;
	}
	public void setProcess_status_note(String process_status_note) {
		this.process_status_note = process_status_note;
	}
	public int getProcess_status_key() {
		return process_status_key;
	}
	public void setProcess_status_key(int process_status_key) {
		this.process_status_key = process_status_key;
	}
	public int getCollection_major_id() {
		return collection_major_id;
	}
	public void setCollection_major_id(int collection_major_id) {
		this.collection_major_id = collection_major_id;
	}
	public String getTemplate_type_string() {
		return template_type_string;
	}
	public void setTemplate_type_string(String template_type_string) {
		this.template_type_string = template_type_string;
	}
	public int getCollection_template_type() {
		return collection_template_type;
	}
	public void setCollection_template_type(int collection_template_type) {
		this.collection_template_type = collection_template_type;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getCollection_template_name() {
		return collection_template_name;
	}
	public void setCollection_template_name(String collection_template_name) {
		this.collection_template_name = collection_template_name;
	}
	public String getProcess_status() {
		return process_status;
	}
	public void setProcess_status(String process_status) {
		this.process_status = process_status;
	}

}
