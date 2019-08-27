package com.philip.edu.eval.bean;

import java.util.Date;

public class BasicForm {
	private int id;
	private int collection_major_id;
	private String major_task_doc;
	private String self_eval_doc;
	private Date create_time;
	private Date update_time;
	private char process_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getCollection_major_id() {
		return collection_major_id;
	}
	public void setCollection_major_id(int collection_major_id) {
		this.collection_major_id = collection_major_id;
	}
	public String getMajor_task_doc() {
		return major_task_doc;
	}
	public void setMajor_task_doc(String major_task_doc) {
		this.major_task_doc = major_task_doc;
	}
	public String getSelf_eval_doc() {
		return self_eval_doc;
	}
	public void setSelf_eval_doc(String self_eval_doc) {
		this.self_eval_doc = self_eval_doc;
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
}
