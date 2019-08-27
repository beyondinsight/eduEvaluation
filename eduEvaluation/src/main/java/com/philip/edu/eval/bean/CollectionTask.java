package com.philip.edu.eval.bean;

import java.util.Date;

public class CollectionTask {
	private int id;
	private String task_name;
	private String task_year;
	private String description;
	private int use_metrics_system;
	private Date create_time;
	private Date update_time;
	private char status;
	private String start_time;
	private String end_time;
	private String memo;
	private int form_basic_weight;
	private int form_performance_weight;
	private int form_capitalprogress_weight;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_year() {
		return task_year;
	}
	public void setTask_year(String task_year) {
		this.task_year = task_year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUse_metrics_system() {
		return use_metrics_system;
	}
	public void setUse_metrics_system(int use_metrics_system) {
		this.use_metrics_system = use_metrics_system;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getForm_basic_weight() {
		return form_basic_weight;
	}
	public void setForm_basic_weight(int form_basic_weight) {
		this.form_basic_weight = form_basic_weight;
	}
	public int getForm_performance_weight() {
		return form_performance_weight;
	}
	public void setForm_performance_weight(int form_performance_weight) {
		this.form_performance_weight = form_performance_weight;
	}
	public int getForm_capitalprogress_weight() {
		return form_capitalprogress_weight;
	}
	public void setForm_capitalprogress_weight(int form_capitalprogress_weight) {
		this.form_capitalprogress_weight = form_capitalprogress_weight;
	}
	
	
	
}
