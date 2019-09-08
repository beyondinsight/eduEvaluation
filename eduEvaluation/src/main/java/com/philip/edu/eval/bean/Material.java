package com.philip.edu.eval.bean;

import java.util.Date;

public class Material {
	private int id;
	private String material_name;
	private String memo;
	private int metrics_id;
	private String file_name;
	private int doc_size;
	private Date create_time;
	private Date update_time;
	private int material_id;
	private char is_required;
	private int form_performance_id;
	private String doc;
	
	
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public int getForm_performance_id() {
		return form_performance_id;
	}
	public void setForm_performance_id(int form_performance_id) {
		this.form_performance_id = form_performance_id;
	}
	public char getIs_required() {
		return is_required;
	}
	public void setIs_required(char is_required) {
		this.is_required = is_required;
	}
	public int getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getDoc_size() {
		return doc_size;
	}
	public void setDoc_size(int doc_size) {
		this.doc_size = doc_size;
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
	public int getMetrics_id() {
		return metrics_id;
	}
	public void setMetrics_id(int metrics_id) {
		this.metrics_id = metrics_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
